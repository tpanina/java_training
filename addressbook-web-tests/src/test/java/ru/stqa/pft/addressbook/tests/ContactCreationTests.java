package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Test name1", "Test surname", "Test address", "123456789", "test@email.ru"));
    app.getContactHelper().submitContactForm();
    app.getNavigationHelper().returnToHomePage();
  }

}
