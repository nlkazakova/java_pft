package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigatioinHelper navigatioinHelper;
    private GroupHelper groupHelper;
    private String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)) {
            wd = new InternetExplorerDriver();
        }

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigatioinHelper = new NavigatioinHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login("admin", "secret");
    }


    public boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    public void stop() {
        wd.findElement(By.linkText("Logout")).click();
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigatioinHelper getNavigatioinHelper() {
        return navigatioinHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}