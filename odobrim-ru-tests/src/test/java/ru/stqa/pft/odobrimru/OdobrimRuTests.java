package ru.stqa.pft.odobrimru;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class OdobrimRuTests {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testOdobrimRu() throws Exception {
    wd.get("https://creditexchange-uat.tusvc.bcs.ru/");
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Подобрать'])[1]/preceding::h3[1]")).click();
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Срок, месяцев'])[1]/following::div[9]")).click();
    wd.findElement(By.linkText("Вход")).click();
    wd.findElement(By.id("phone")).click();
//    wd.findElement(By.id("phone")).clear();
    wd.findElement(By.id("phone")).sendKeys("9200000007");
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Телефон'])[1]/following::div[2]")).click();
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Получить код'])[1]/preceding::label[1]")).click();
    wd.findElement(By.id("sms")).click();
//    wd.findElement(By.id("sms")).clear();
    wd.findElement(By.id("sms")).sendKeys("1234");
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Далее'])[1]/following::div[3]")).click();
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Рейтинг'])[1]/following::div[9]")).click();
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Условия и ставки'])[1]/following::button[1]")).click();
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Подать заявку'])[1]/preceding::button[1]")).click();
    wd.findElement(By.id("expand_options")).click();
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Свернуть'])[1]/following::div[9]")).click();
    wd.findElement(By.id("downshift-5-item-1")).click();
    wd.findElement(By.id("expand_options")).click();
    wd.findElement(By.id("dropdown_profile")).click();
    wd.findElement(By.id("dropdown_account")).click();
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Федор'])[1]/following::p[1]")).click();
    wd.findElement(By.id("dropdown_exit")).click();
  }

  @AfterMethod(alwaysRun = true)
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
