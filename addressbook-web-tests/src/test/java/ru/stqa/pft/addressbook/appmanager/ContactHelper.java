package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {

        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFname());
        type(By.name("middlename"), contactData.getMname());
        type(By.name("lastname"), contactData.getLname());
        type(By.name("nickname"), contactData.getNname());
        type(By.name("theform"), contactData.getPhoneNumber());
        type(By.name("email"), contactData.getEmail());
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initContactCreation() {
      click(By.linkText("add new"));
    }
}
