package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification () {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Test name1", "Test surname", "Test address", "123456789", "test@email.ru", "Test Group"));
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().editContactForm();
        app.getContactHelper().fillContactForm(new ContactData("Edit name1", "Edit surname", "Edit address", null, "edit@email.ru", null), false);
        app.getContactHelper().updateContactForm();
        app.getContactHelper().returnToHomePageWithContacts();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
