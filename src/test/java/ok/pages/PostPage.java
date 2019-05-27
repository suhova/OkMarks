package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ok.pages.Helper.isElementPresent;

public class PostPage extends BasePage{
    private final By MENU_TOP = By.id("mainTopContentRow");
    private final By NEW_POST = By.id("hook_Block_PostingForm");
    private final By MENU_LEFT = By.id("hook_Block_MediaTopicDisplayTypeFilter");
    private final By ADD_POST = By.xpath(".//*[@id='hook_Block_PostingForm']//*[@class='pf-head_itx_a']");
    private final By WRITE = By.xpath(".//*[@data-module='postingForm/mediaText']");
    private final By SUBMIT = By.xpath(".//*[@title='Поделиться']");
    private final By POST = By.xpath(".//*[@tsid='userStatusShares']//*[@class='media-text_a']");
    private final By ARROW = By.xpath(".//*[@class='ic12 ic12_arrow-down lp']");
    private final By MARK = By.xpath(".//*[@class='mlr_top_ac']//*[@class='tico_img ic ic_bookmark-g']");


    WebDriver driver;

    public PostPage(WebDriver driver) {
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
        Assert.assertTrue("Нет блока нового поста",new WebDriverWait(driver, 3).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(NEW_POST, driver);
            }
        }));
        Assert.assertTrue("Нет левого блока меню",new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d){
                return isElementPresent(MENU_LEFT, driver);
            }
        }));
    }

    public PostPage addPost(){
        if(isElementPresent(ADD_POST, driver)) {
            driver.findElement(ADD_POST).click();
            driver.findElement(WRITE).sendKeys(":)");
            driver.findElement(SUBMIT).click();
        }
        return this;
    }

    public PostPage addMark(){
        if(isElementPresent(POST,driver)){
            driver.findElement(POST).click();
            driver.findElement(ARROW).click();
            System.out.println(2);
            driver.findElement(MARK).click();
            System.out.println(3);
        }
        return this;
    }
}
