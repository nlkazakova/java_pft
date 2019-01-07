package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

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
        Set<GroupData> notEmptyGroups = app.db().groups().stream().filter((g) -> app.db().contactsInGroup(g).size() > 0).collect(Collectors.toSet());
        if (notEmptyGroups.size() == 0) {
            app.contact().addContactToGroup(app.db().contacts().iterator().next(), app.db().groups().iterator().next());
        }
    }

    @Test
    public void testRemoveContactFromGroup() {
        for (GroupData selectedGroup: app.db().groups()) {
            logger.info("Выбрана группа " + selectedGroup.getName());
            app.goTo().ContactInGroupPage(selectedGroup);
            Contacts contactsInGroup = app.db().contactsInGroup(selectedGroup);
            if (contactsInGroup.size() == 0) {
                logger.info("В группе нет ни одного контакта, выбираем следующиую группу");
            } else {
                Contacts before = app.db().contactsInGroup(selectedGroup);
                ContactData selectedContact = contactsInGroup.iterator().next();
                app.contact().removeFromGroup(selectedGroup, selectedContact);
                logger.info("Контакт " + selectedContact.getFname() + " удален из группы " + selectedGroup.getName());
                Contacts after = app.db().contactsInGroup(selectedGroup);
                assertThat(app.contact().count(), equalTo(before.size() - 1));
                assertThat(after, equalTo(before.without(selectedContact.inGroup(selectedGroup))));

                verifyContactsInGroupUI(selectedGroup);
                return;
            }
        }
    }


}
