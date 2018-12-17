package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigatioinHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().GetGroupList();
    app.getGroupHelper().cresteGroup(new GroupData("test1", null, null));
    List<GroupData> after = app.getGroupHelper().GetGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
