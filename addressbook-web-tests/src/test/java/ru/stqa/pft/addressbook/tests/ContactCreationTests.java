package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().creste(new GroupData().withName("test1"));
    }
    app.goTo().HomePage();
  }

  @Test
  public void testContactCreation() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFname("Natalia").withMname("Leonidovna").withLname("Kazakova").withNname("nlkazakova").withPhoneNumber("+79166752495").withEmail("nlkazakova9@gmail.com").withGroup("test1");
    app.contact().addNew();
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
