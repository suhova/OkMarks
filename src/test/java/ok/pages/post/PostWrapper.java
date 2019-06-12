package ok.pages.post;

import ok.pages.BookmarkPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static ok.pages.Helper.isElementPresent;
import static ok.pages.Helper.isElementVisible;

public class PostWrapper {

    private WebElement cardPost;
    private final By ARROW = By.xpath(".//*[@class='ic12 ic12_arrow-down lp']");
    private final static By TEXT = By.xpath(".//*[@class='media-text_cnt_tx emoji-tx textWrap']");
    private final By MARK = By.xpath(".//*[@class='mlr_top_ac']//*[@class='tico_img ic ic_bookmark-g']");
    private final By CLOSE = By.xpath(".//*[@class='ic media-layer_close_ico']");
    private final By DELETE = By.xpath(".//*[@class='feed_close']");


    public PostWrapper(WebElement post) {
        this.cardPost = post;
    }

    public String getMessage() {
        return cardPost.findElement(TEXT).getText();
    }

    public PostPage click(WebDriver driver) {
        cardPost.click();
        return new PostPage(driver);
    }

    public static PostWrapper getPostByText(String message, WebDriver driver, List<PostWrapper> posts) {
        for (PostWrapper card : posts) {
            //     if(!) return null;
            if (isElementPresent(TEXT, driver) && card.getMessage().contains(message)) {
                return card;
            }
        }
        return null;
    }

    // Удалить заметку из закладок
    public BookmarkPage deleteMark(WebDriver driver) {
        By delete = By.xpath(".//*[@title='Убрать из закладок']");
        Assert.assertTrue("Нет крестика удаления из закладок", isElementPresent(delete, driver));
        cardPost.findElement(delete).click();
        return new BookmarkPage(driver);
    }

    // Удалить заметку в принципе
    public PostPage deletePost(WebDriver driver) {
        Assert.assertTrue("Нет крестика удаления поста", isElementPresent(DELETE, driver));
        Actions action = new Actions(driver);
        WebElement del = driver.findElement(DELETE);
        action.moveToElement(del).perform();
        Assert.assertTrue("Не видно крестика удаления поста", isElementVisible(DELETE, driver));
        del.click();
        return new PostPage(driver);
    }

    // Добавить заметку в закладки
    public PostPage addMark(WebDriver driver) {
        Actions actions = new Actions(driver);

        Assert.assertTrue("Нет стрелочки для добавления в закладки", isElementPresent(ARROW, driver));
        actions.moveToElement(driver.findElement(ARROW));

        Assert.assertTrue("Нет добавления в закладки", isElementPresent(MARK, driver));
        WebElement element = driver.findElement(MARK);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);

        Assert.assertTrue("Нет кнопки закрытия заметки", isElementPresent(CLOSE, driver));
        driver.findElement(CLOSE).click();
        return new PostPage(driver);
    }

}
