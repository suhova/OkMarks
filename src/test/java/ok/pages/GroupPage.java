package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ok.pages.Helper.isElementPresent;

public class GroupPage extends BasePage{
    private final By NAVIGATION_TOOLBAR = By.xpath("//*[@class='toolbar_c']");
    private final By MARK = By.id("hook_Block_inplrmbm_TopCard_53245288710321_4");
    private final By MAIN_BLOCK = By.id("hook_Block_MainContentHeader");


    WebDriver driver;

    public GroupPage(WebDriver driver) {
        this.driver = driver;
        check(driver);
    }

    @Override
    void check(WebDriver driver) {
        Assert.assertTrue("Нет шапки группы",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(MAIN_BLOCK, driver);
            }
        }));
        Assert.assertTrue("Нет блока добавления в закладки",new WebDriverWait(driver, 3).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(MARK, driver);
            }
        }));
        Assert.assertTrue("Нет блока меню сверху",new WebDriverWait(driver, 3).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(NAVIGATION_TOOLBAR, driver);
            }
        }));
    }

    public GroupPage addMark() {
        if(isElementPresent(MARK, driver)) {
            driver.findElement(MARK).click();
        }
        return this;
    }

    public boolean markStatus() {
        if(isElementPresent(MARK, driver)) {
            String status = driver.findElement(MARK).getText();
            if (status.equals("В закладки")){
                return true;
            }
        }
        return false;
    }

}
