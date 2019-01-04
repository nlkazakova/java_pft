package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().GroupPage();
                app.group().create(new GroupData().withName("test1"));
            }
            app.goTo().HomePage();
            app.contact().addNew();
            app.contact().create(new ContactData().withFname("Natalia").withLname("Kazakova"));
        }
        app.goTo().HomePage();
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFname("Natalia")
                .withLname("Kazakova").withPhoto(new File("src/test/resources/pp_my.jpg"))
                .withMobilePhone("12345678").withEmail("nlkazakova9@gmail.com").withAddress("г. Москва, Красная площадь, д.1");
        app.contact().modify(contact);
        app.goTo().HomePage();
        Contacts after = app.db().contacts();
        assertEquals(app.contact().count(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }

}
