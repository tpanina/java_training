package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification () {
        app.getNavigationHelper().gotoHomePage();
        /*int before = app.getContactHelper().getContactCount();*/
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Test name1", "Test surname", "Test address", "123456789", "test@email.ru", "Test Group"));
        }
        app.getContactHelper().editContactForm();
        app.getContactHelper().fillContactForm(new ContactData("Edit name1", "Edit surname", "Edit address", "123456789", "edit@email.ru", null));
        app.getContactHelper().updateContactForm();
        app.getContactHelper().returnToHomePageWithContacts();
        /*int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);*/
    }
}
