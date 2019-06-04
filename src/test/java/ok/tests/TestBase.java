package ok.tests;

import ok.pages.LoginPage;
import ok.pages.UserMainPage;
import org.junit.After;
import org.openqa.selenium.*;

import static org.junit.Assert.fail;

public class TestBase {
    private StringBuffer verificationErrors = new StringBuffer();
    protected String baseUrl = "https://ok.ru/";
    protected String login = "technopolisBot206";
    protected String password = "technopolis16";
    protected WebDriver driver;
    private boolean acceptNextAlert = true;

    protected UserMainPage login(){
        driver.get(baseUrl + "/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginEnter(login);
        loginPage.passwordEnter(password);
       return loginPage.clickEnter();
    }

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
    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}