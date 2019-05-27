package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ok.pages.Helper.isElementPresent;

public class GroupsPage extends BasePage{
    private final By MENU_TOP = By.id("mainTopContentRow");
    private final By POPULAR = By.id("hook_Block_PopularGroupsListBlock");
    private final By MENU_LEFT = By.id("hook_Block_UserGroupsCatalogRBlock");
    private final By SEARCH = By.id("query_userAltGroupSearch");
    private final By GROUP = By.xpath("//*[@id='userGroupsSearchResultList']//*[@class='photo']");

    WebDriver driver;

    public GroupsPage(WebDriver driver) {
        this.driver = driver;
        check(driver);
    }

    @Override
    void check(WebDriver driver) {
        Assert.assertTrue("Нет блока меню сверху",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(MENU_TOP, driver);
            }
        }));
        Assert.assertTrue("Нет блока популярное",new WebDriverWait(driver, 3).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(POPULAR, driver);
            }
        }));
        Assert.assertTrue("Нет блока меню слева",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(MENU_LEFT, driver);
            }
        }));
        Assert.assertTrue("Нет поля поиска групп",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(SEARCH, driver);
            }
        }));
    }

    public GroupPage open (String name) {
        if(isElementPresent(SEARCH, driver)) {
            driver.findElement(SEARCH).clear();
            driver.findElement(SEARCH).sendKeys(name);
        }
        if(isElementPresent(GROUP, driver)) {
            driver.findElement(GROUP).click();
        }
        return new GroupPage(driver);
    }

}


