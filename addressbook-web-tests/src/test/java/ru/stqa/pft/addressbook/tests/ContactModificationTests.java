package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test

    public void testContactModification () {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().editContactForm();
        app.getContactHelper().fillContactForm(new ContactData("Edit name1", "Edit surname", "Edit address", "123456789", "edit@email.ru"));
        app.getContactHelper().updateContactForm();
        app.getNavigationHelper().returnToHomePage();
    }
}
