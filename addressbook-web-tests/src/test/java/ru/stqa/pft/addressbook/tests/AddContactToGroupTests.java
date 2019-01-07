package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class AddContactToGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().GroupPage();
                app.group().create(new GroupData().withName("test").withHeader("header").withFooter("footer"));
            }
            app.goTo().HomePage();
            app.contact().addNew();
            app.contact().create(new ContactData().withFname("Natalia").withLname("Kazakova").withAddress("г. Москва, Ленинский проспект, д.42, кв.45").withMobilePhone("+700000001").withEmail("test@test.test").withPhoto(new File("src/test/resources/pp_my.jpg")).inGroup(app.db().groups().iterator().next()));
        }
    }
    @Test
    public void testAddContactToGroup() {
        for (ContactData contact: app.db().contacts()) {
            logger.info("Выбран контакт " + contact.getFname());
            Groups availableGroups = app.db().groups();
            if (contact.getGroups().size() == availableGroups.size()) {
                logger.info("Выбранный контакт содержится во всех группах, выбираем следущий контакт");
            } else {
                availableGroups.removeAll(contact.getGroups());
                logger.info("Для контакта " + contact.getFname() + " доступны следующие группы: " + availableGroups.stream().map(GroupData::getName).collect(Collectors.toSet()));
                GroupData mappedGroup = availableGroups.iterator().next();

                Contacts before = app.db().contactsInGroup(mappedGroup);

                app.goTo().HomePage();
                app.contact().addContactToGroup(contact, mappedGroup);

                logger.info("Контакт " + contact.getFname() + " добавлен в группу " + mappedGroup.getName());

                Contacts after = app.db().contactsInGroup(mappedGroup);

                assertThat(app.contact().count(), equalTo(before.size() + 1));
                assertThat(after, equalTo(before.withAdded(contact.inGroup(mappedGroup))));

                verifyContactsInGroupUI(mappedGroup);
                return;
            }
        }
    }
}
