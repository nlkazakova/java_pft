package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigatioinHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().cresteGroup(new GroupData("test1", null, null));
    }
    app.getContactHelper().initContactCreation();
    app.getContactHelper().createContact(new ContactData("Natalia", "Leonidovna", "Kazakova", "nlkazakova", "+79166752495", "nlkazakova9@gmail.com", "test1"));
    app.getNavigatioinHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
