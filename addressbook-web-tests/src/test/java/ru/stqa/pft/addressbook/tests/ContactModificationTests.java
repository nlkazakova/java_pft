package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (app.contact().list().size() == 0) {
            app.goTo().GroupPage();
            if (app.group().all().size() == 0) {
                app.group().creste(new GroupData().withName("test1"));
            }
            app.contact().addNew();
            app.contact().create(new ContactData().withFname("Natalia").withMname("Leonidovna").withLname("Kazakova").withNname("nlkazakova").withPhoneNumber("+79166752495").withEmail("nlkazakova9@gmail.com"));
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withId(before.get(index).getId()).withFname("Natalia").withMname("Leonidovna").withLname("Kazakova").withNname("nlkazakova").withPhoneNumber("+79166752495").withEmail("nlkazakova9@gmail.com");
        app.contact().modify(index, contact);
        app.goTo().HomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());
        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
