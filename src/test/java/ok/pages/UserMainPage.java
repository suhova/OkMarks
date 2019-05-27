package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ok.pages.Helper.isElementPresent;

public class UserMainPage extends BasePage {
    WebDriver driver;
    private final By NAVIGATION_TOOLBAR = By.xpath("//*[@class='toolbar_c']");
    private final By MAIN_CONTENT = By.id("hook_Block_UserMainFullMRB");
    private final By MENU_LEFT = By.id("hook_Block_SideNavigation");
    private final By AVATAR = By.id("hook_Block_Avatar");

    public UserMainPage(WebDriver driver) {
        this.driver = driver;
        check(driver);
    }
    @Override
    void check( WebDriver driver) {
        Assert.assertTrue("Нет основаного блока",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(MAIN_CONTENT, driver);
            }
        }));
        Assert.assertTrue("Нет блока навигациии",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(NAVIGATION_TOOLBAR, driver);
            }
        }));
        Assert.assertTrue("Нет блока меню слева",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(MENU_LEFT, driver);
            }
        }));
        Assert.assertTrue("Нет аватара",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(AVATAR, driver);
            }
        }));
    }
}
