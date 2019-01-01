package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
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
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFname("Natalia").withMname("Leonidovna").withLname("Kazakova").withNname("nlkazakova").withPhoneNumber("+79166752495").withEmail("nlkazakova9@gmail.com");
        app.contact().modify(contact);
        app.goTo().HomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
