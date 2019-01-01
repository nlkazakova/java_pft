package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFname());
        type(By.name("middlename"), contactData.getMname());
        type(By.name("lastname"), contactData.getLname());
        type(By.name("nickname"), contactData.getNname());
        type(By.name("mobile"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());

        if(creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        }
        else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addNew() {
      click(By.linkText("add new"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactModification(int index) {
        wd.findElements(By.cssSelector("img[alt=\"Edit\"]")).get(index).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contactData) {
        addNew();
        fillContactForm(contactData, true);
        submitContactCreation();
        wd.findElement(By.cssSelector("img[alt=\"Addressbook\"]")).click();
    }

    public void modify(int index, ContactData contact) {
        selectContact(index);
        initContactModification(index);
        fillContactForm(contact, false);
        submitContactModification();

    }
    public void delete(int index) {
        selectContact(index);
        deleteSelectedContact();
        wd.findElement(By.cssSelector("img[alt=\"Addressbook\"]")).click();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> tableRows = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
                int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
                String lastname = cells.get(1).getText();
                String firstname = cells.get(2).getText();
                String email = cells.get(4).getText();
                String phone = cells.get(5).getText();
                contacts.add(new ContactData().withId(id).withFname(firstname).withLname(lastname).withEmail(email).withPhoneNumber(phone));
            }
        return contacts;
        }


}
