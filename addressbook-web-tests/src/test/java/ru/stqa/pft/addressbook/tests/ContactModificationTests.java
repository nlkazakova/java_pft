package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigatioinHelper().gotoGroupPage();
            if (! app.getGroupHelper().isThereAGroup()) {
                app.getGroupHelper().cresteGroup(new GroupData("test1", null, null));
            }
            app.getContactHelper().initContactCreation();
            app.getContactHelper().createContact(new ContactData("Natalia", "Leonidovna", "Kazakova", "nlkazakova", "+79166752495", "nlkazakova9@gmail.com", "null"));
        }
        app.getNavigatioinHelper().returnToHomePage();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "Kazakova", "Natalia", "nlkazakova9@gmail.com", "+79166752495");
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getNavigatioinHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
