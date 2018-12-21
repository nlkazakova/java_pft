package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.getContactHelper().getContactList();
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigatioinHelper().gotoGroupPage();
            if (! app.getGroupHelper().isThereAGroup()) {
                app.getGroupHelper().cresteGroup(new GroupData("test1", null, null));
            }
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Natalia", "Leonidovna", "Kazakova", "nlkazakova", "+79166752495", "nlkazakova9@gmail.com", "test1"));
            app.getNavigatioinHelper().returnToHomePage();
        }

        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getNavigatioinHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
