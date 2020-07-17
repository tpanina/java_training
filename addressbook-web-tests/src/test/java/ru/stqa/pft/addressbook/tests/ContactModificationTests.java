package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Test name1", "Test surname", "Test address", "123456789", "test@email.ru", "Test Group"));
        }
    }

    @Test(enabled = false)
    public void testContactModification () {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(),"Edit name", "Edit surname", "Edit address", null, "edit@email.ru", null);
        app.getContactHelper().modifyContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        /* before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byID = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byID);
        after.sort(byID);
        Assert.assertEquals(before, after); */

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
