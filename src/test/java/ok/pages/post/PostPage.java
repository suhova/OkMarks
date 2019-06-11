package ok.pages.post;

import ok.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ok.pages.CardTransformer.wrapPost;
import static ok.pages.post.PostWrapper.getPostByText;

public class PostPage extends BasePage {
    private final By MENU_TOP = By.id("mainTopContentRow");
    private final By NEW_POST = By.id("hook_Block_PostingForm");
    private final By MENU_LEFT = By.id("hook_Block_MediaTopicDisplayTypeFilter");
    private final By ADD_POST = By.xpath(".//*[@id='hook_Block_PostingForm']//*[@class='pf-head_itx_a']");
    private final By WRITE = By.xpath(".//*[@data-module='postingForm/mediaText']");
    private final By SUBMIT = By.xpath(".//*[@class='posting_f']//*[@class='posting_submit button-pro']");

    public PostPage(WebDriver driver) {
        super.driver = driver;
        check(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались блока меню сверху",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU_TOP), 10, 500));
        Assert.assertTrue("Не дождались блока нового поста",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(NEW_POST), 10, 500));
        Assert.assertTrue("Не дождались блока меню",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU_LEFT), 10, 500));
    }
    // Добавить новую заметку
    // без скрипта
    // проверять визабилити
    public PostPage addPost(String message){
        Assert.assertTrue("Не найдено поля добавления нового поста", isElementPresent(ADD_POST));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(ADD_POST));
        Assert.assertTrue("Не найдено поля написать заметку", isElementPresent(WRITE));
        driver.findElement(WRITE).sendKeys(message);
        Assert.assertTrue("Не найдено кнопки добавления заметки", isElementPresent(SUBMIT));
        driver.findElement(SUBMIT).click();
        return this;
    }
    // Получить заметку по тексту
    public PostWrapper getPostWrapperByText(String message){
        By postsBy = By.xpath(".//*[@id='hook_Block_UserStatusesMRB']//*[@class='feed']");
        Assert.assertTrue("Не найдено списка заметок", isElementPresent(postsBy));
        List<PostWrapper> posts = wrapPost(driver.findElements(postsBy));
        return  getPostByText(message,driver, posts);
    }
}
