package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contact().create(new ContactData().withFirstname("Test name").withLastname("Test surname").withAddress("Test address").withHomePhone("123456789").withEmail("test@email.ru"));
        }

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test Group").withHeader("Test header").withFooter("Test footer"));
        }

        if (app.db().contacts().iterator().next().getGroups().size() != 0) {
            app.contact().removeContactFromGroup(app.db().contacts().iterator().next(), app.db().groups().iterator().next().getId());
        }
    }

    @Test
    public void testAddingContactToGroup() {
        app.goTo().HomePage();
        Contacts beforeContacts = app.db().contacts();
        Groups beforeGroups = app.db().groups();
        ContactData modifiedContact = beforeContacts.iterator().next();
        GroupData addedGroup = beforeGroups.iterator().next();
        app.contact().addContactToGroup(modifiedContact, addedGroup.getId());
        //берем модифицированный контакт, запрашиваем у него список групп и берем координаты добавленной группы
        Groups groupInContact = modifiedContact.getGroups().withAdded(addedGroup);
        //берем список всех контактов, находим в списке модифицированный контакт, берем его данные, смотрим список групп у этого контакта
        Groups afterGroups = app.db().contacts().getContactById(modifiedContact.getId()).getGroups();
        // Проверка № 1
        assertThat(groupInContact, equalTo(afterGroups));

        // Запрашиваем булевское значение, действительно ли группа содержит модифицированный контакт
        boolean groupContainsContact = addedGroup.getContacts().add(modifiedContact);
        // Запрашиваем булевское значение, действительно ли модицифированный контакт содержит группу
        boolean contactContainsGroup = modifiedContact.getGroups().add(addedGroup);
        // Проверка № 2, которая возращает булевские значения
        assertThat(groupContainsContact, equalTo(contactContainsGroup));

        System.out.println("Data of the added group is " + groupInContact);
        System.out.println("Data of the added group (taken from the DB) is " + afterGroups);
        System.out.println("Boolean value, confirming that the added group contains data of the modified contact, returns " + groupContainsContact);
        System.out.println("Boolean value, confirming that the modified contact contains data of the changed group, returns " + contactContainsGroup);
    }
}
