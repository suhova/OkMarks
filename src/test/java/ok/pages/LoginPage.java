package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ok.pages.Helper.isElementPresent;

public class LoginPage extends BasePage{
    private final By LOGIN = By.id("field_email");
    private final By ENTER = By.xpath("//form//input[@type='submit']");
    private final By PASSWORD = By.id("field_password");

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        check(driver);
    }

    @Override
    void check(WebDriver driver) {
        Assert.assertTrue("Нет поля пароля",new WebDriverWait(driver, 3).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(PASSWORD, driver);
            }
        }));

        Assert.assertTrue("Нет поля логина",new WebDriverWait(driver, 3).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(LOGIN, driver);
            }
        }));
        Assert.assertTrue("Нет поля войти",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(ENTER, driver);
            }
        }));
    }

    public LoginPage loginEnter(String str) {
        if(isElementPresent(LOGIN, driver)) {
            driver.findElement(LOGIN).clear();
            driver.findElement(LOGIN).sendKeys(str);
        }
            return this;
    }
    public LoginPage passwordEnter(String str) {
        if(isElementPresent(PASSWORD, driver)) {
            driver.findElement(PASSWORD).clear();
            driver.findElement(PASSWORD).sendKeys(str);
        }
            return this;
    }

    public UserMainPage clickEnter() {
        if(isElementPresent(ENTER, driver)) {
            driver.findElement(ENTER).click();
        }
            return new UserMainPage(driver);
    }



}

