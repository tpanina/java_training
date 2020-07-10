package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

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
        type(By.name("home"), contactData.getHomephone());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void createContact(ContactData contact) {
        gotoAddContactPage();
        fillContactForm(contact, true);
        submitContactForm();
        returnToHomePageWithContacts();
    }

    public void editContactForm() {
        click(By.xpath("(//img[@alt='Edit'])"));
    }

    public void updateContactForm() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void selectContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        driver.switchTo().alert().accept();
    }

    public int getContactCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public void returnToHomePageWithContacts() {

        click(By.linkText("home page"));
    }

    public void gotoAddContactPage() {

        click(By.linkText("add new"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }
}