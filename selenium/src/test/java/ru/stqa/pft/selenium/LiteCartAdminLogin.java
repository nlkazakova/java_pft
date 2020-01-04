package ru.stqa.pft.selenium;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LiteCartAdminLogin {
  private WebDriver wd;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLiteCartAdminLogin() throws Exception {
    wd.get("http://localhost/litecart/admin/login.php?redirect_url=%2Flitecart%2Fadmin%2F");
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Username'])[1]/following::td[1]")).click();
    wd.findElement(By.name("username")).click();
    wd.findElement(By.name("username")).sendKeys("admin");
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password'])[1]/following::td[1]")).click();
    wd.findElement(By.name("password")).click();
    wd.findElement(By.name("password")).sendKeys("admin");
    wd.findElement(By.name("login")).click();
    wd.findElement(By.xpath("//td[@id='sidebar']/div[2]/a[5]/i")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
