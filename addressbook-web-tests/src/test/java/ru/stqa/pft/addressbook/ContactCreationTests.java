package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    gotoContactPage();
    fillContactForm(new ContactData("Test name1", "Test surname", "Test address", "123456789", "test@email.ru"));
    submitContactForm();
    returnToHomePage();
  }

}
