package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contact().create(new ContactData().withFirstname("Test name").withLastname("Test surname").withAddress("Test address").withHomePhone("123456789").withEmail("test@email.ru").withPhoto(new File("src/test/resources/ava.jpg")));
        }
    }

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test (dataProvider = "validContacts")
    public void testContactModification(ContactData contact) {
        app.contact().gotoAddContactPage();
        app.contact().create(contact);
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contactModify = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Edit name").withLastname("Edit surname")
                .withAddress("Edit address").withHomePhone("6575849").withMobilePhone("12345").withWorkPhone("900229")
                .withEmail("edit@email.ru").withEmail2("onemore@edit.com").withEmail3("thelastone@edit.com")
                .withPhoto(new File("src/test/resources/ava.jpg"));
        app.contact().modify(contactModify);
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactModify)));
    }
}
