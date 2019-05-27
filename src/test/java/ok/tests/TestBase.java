package ok.tests;

import ok.pages.*;
import org.junit.Assert;
import org.openqa.selenium.Alert;
        import org.openqa.selenium.By;
        import org.openqa.selenium.NoAlertPresentException;
        import org.openqa.selenium.NoSuchElementException;
        import org.openqa.selenium.WebDriver;

import java.util.List;

import static ok.pages.BasePage.openMark;
import static ok.pages.CardTransformer.wrapMark;
import static ok.pages.CardTransformer.wrapMenu;
import static ok.pages.MarkWrapper.checkMark;
import static ok.pages.MenuWrapper.clickByName;

public class TestBase {

    protected WebDriver driver;
    private boolean acceptNextAlert = true;

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }


}