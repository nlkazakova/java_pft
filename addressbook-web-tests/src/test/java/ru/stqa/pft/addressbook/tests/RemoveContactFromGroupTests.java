package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RemoveContactFromGroupTests extends TestBase {

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
