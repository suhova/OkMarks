package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ok.pages.Helper.isElementPresent;

public class BookmarkPage extends BasePage {

    private final By MENU_TOP = By.id("mainTopContentRow");
    private final By MENU_LEFT = By.id("hook_Block_BookmarksMenuRB");
    private final By MAIN_BLOCK = By.id("hook_Block_BookmarksVitrinaRB");
    private final By MARK_LIST = By.xpath(".//*[@class='bk_list bk_list__altgroup  bk_list__front']//*[@class='cardsList']");


    WebDriver driver;

    public BookmarkPage(WebDriver driver) {
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
        Assert.assertTrue("Нет основного блока",new WebDriverWait(driver, 3).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(MAIN_BLOCK, driver);
            }
        }));
        Assert.assertTrue("Нет блока меню слева",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(MENU_LEFT, driver);
            }
        }));
    }


}
