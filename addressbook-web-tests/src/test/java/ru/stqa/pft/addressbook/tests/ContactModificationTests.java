package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

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
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFname("Natalia").withMname("Leonidovna").withLname("Kazakova").withNname("nlkazakova").withPhoneNumber("+79166752495").withEmail("nlkazakova9@gmail.com");
        app.contact().modify(contact);
        app.goTo().HomePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
