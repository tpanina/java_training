package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

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

        if (app.db().contacts().iterator().next().getGroups().size() == 0) {
            app.contact().addContactToGroup(app.db().contacts().iterator().next(), app.db().groups().iterator().next().getId());
        }
    }

    @Test
    public void testRemovingContactFromGroup() {
        app.goTo().HomePage();
        Contacts beforeContacts = app.db().contacts();
        Groups beforeGroups = app.db().groups();
        ContactData deletingContact = beforeContacts.iterator().next();
        GroupData modifiedGroup = beforeGroups.iterator().next();
        app.contact().removeContactFromGroup(deletingContact, modifiedGroup.getId());


        //берем редактируемый контакт, запрашиваем у него список групп и берем координаты измененной группы
        Groups groupInContact = deletingContact.getGroups().without(modifiedGroup);
        //берем список всех контактов, находим в списке редактируемый контакт, берем его данные, смотрим список групп у этого контакта
        Groups afterGroups = app.db().contacts().getContactById(deletingContact.getId()).getGroups();
        // Проверка данных
        assertThat(groupInContact, equalTo(afterGroups));


        System.out.println("Data of the modified group is Null and the value returns " + groupInContact);
        System.out.println("Data of the modified group (taken from the DB) is Null and the value returns " + afterGroups);
    }
}
