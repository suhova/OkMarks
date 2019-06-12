package ok.pages.post;

import ok.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
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
    private final By POST_LIST = By.xpath(".//*[@id='hook_Block_UserStatusesMRB']//*[@class='feed']");
    private final By EMPTY_BLOCK = By.xpath(".//*[@class='stub-empty __statuses ']");

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
    public PostPage addPost(String message) {
        Assert.assertTrue("Не найдено поля добавления нового поста", isElementVisible(ADD_POST));
        driver.findElement(ADD_POST).click();
        Assert.assertTrue("Не найдено поля написать заметку", isElementVisible(WRITE));
        driver.findElement(WRITE).sendKeys(message);
        Assert.assertTrue("Не найдено кнопки добавления заметки", isElementVisible(SUBMIT));
        driver.findElement(SUBMIT).click();
        return this;
    }

    // Получить заметку по тексту
    public PostWrapper getPostWrapperByText(String message) {
        Assert.assertTrue("Не найдено списка заметок", isElementPresent(POST_LIST));
        List<PostWrapper> posts = wrapPost(driver.findElements(POST_LIST));
        return getPostByText(message, driver, posts);
    }

    //удалить все заметки
    public PostPage deleteAllPosts() {
        if (isElementVisible(EMPTY_BLOCK)) return this;
        Assert.assertTrue("Не найдено списка заметок", isElementPresent(POST_LIST));
        List<PostWrapper> posts = wrapPost(driver.findElements(POST_LIST));
        for (PostWrapper post : posts) {
            post.deletePost(driver);
            driver.navigate().refresh();
        }
        return this;
    }
}
