package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private final By LOGIN = By.id("field_email");
    private final By ENTER = By.xpath("//form//input[@type='submit']");
    private final By PASSWORD = By.id("field_password");

    public LoginPage(WebDriver driver) {
        super.driver = driver;
        check(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались поля пароля",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(PASSWORD), 10, 500));

        Assert.assertTrue("Не дождались поля логина",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LOGIN), 10, 500));

        Assert.assertTrue("Не дождались кнопки войти",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(ENTER), 10, 500));

    }

    public LoginPage loginEnter(String str) {
        Assert.assertTrue("Нет поля поиска групп", isElementVisible(LOGIN));
        driver.findElement(LOGIN).clear();
        driver.findElement(LOGIN).sendKeys(str);
        return this;
    }

    public LoginPage passwordEnter(String str) {
        Assert.assertTrue("Нет поля поиска групп", isElementVisible(PASSWORD));
        driver.findElement(PASSWORD).clear();
        driver.findElement(PASSWORD).sendKeys(str);
        return this;
    }

    public UserMainPage clickEnter() {
        Assert.assertTrue("Нет кнопки входа", isElementVisible(ENTER));
        driver.findElement(ENTER).click();
        return new UserMainPage(driver);
    }


}

