package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;

public class NavigatioinHelper extends HelperBase {

    public NavigatioinHelper(WebDriver wd) {
        super(wd);
    }

    public void GroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }

    public void HomePage() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        wd.findElement(By.cssSelector("img[alt=\"Addressbook\"]")).click();
    }

    public void ContactInGroupPage(GroupData group) {
        new Select(wd.findElement(By.cssSelector("select[name=\"group\"]"))).selectByVisibleText(group.getName());
    }
}
