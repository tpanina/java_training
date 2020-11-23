package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {

        super(driver);
    }

    public void submitContactForm() {

        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        // attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void create(ContactData contact) {
        gotoAddContactPage();
        fillContactForm(contact, true);
        submitContactForm();
        contactCache = null;
        returnToHomePageWithContacts();
    }

    public void modify(ContactData contact) {
        editContactFormByID(contact.getId());
        fillContactForm(contact, false);
        updateContactForm();
        contactCache = null;
        returnToHomePageWithContacts();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCache = null;
    }

    public void editContactFormByID(int id) {
        click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
    }

    public void updateContactForm() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void selectContactById(int id) {
        driver.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        isAlertPresent(driver);
    }

    public void returnToHomePageWithContacts() {

        click(By.linkText("home page"));
    }

    public void gotoAddContactPage() {

        click(By.linkText("add new"));
    }

    public ContactData infoFromEditForm(ContactData contact) {
        editContactFormByID(contact.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        driver.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if(contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAddress(address).withAllEmails(allEmails)
                    .withAllPhones(allPhones);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public void addContactToGroup(ContactData contact, int groupID) {
        selectContactById(contact.getId());
        driver.findElement(By.name("to_group")).click();
        new Select(driver.findElement(By.name("to_group"))).selectByValue(String.valueOf(groupID));
        driver.findElement(By.xpath("(//option[@value='" + groupID + "'])[2]")).click();
        click(By.xpath("//input[@value='Add to']"));
        driver.findElement(By.partialLinkText("group page")).click();
        contactCache = null;
    }

    public void removeContactFromGroup(ContactData contact, int groupID) {
        driver.findElement(By.name("group")).click();
        new Select(driver.findElement(By.name("group"))).selectByValue(String.valueOf(groupID));
        driver.findElement(By.xpath("(//option[@value=" + groupID + "])[2]")).click();
        selectContactById(contact.getId());
        driver.findElement(By.name("remove")).click();
        driver.findElement(By.partialLinkText("group page")).click();
        new Select(driver.findElement(By.name("group"))).selectByVisibleText("[all]");
        contactCache = null;
    }
}