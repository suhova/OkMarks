package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static ok.pages.Helper.isElementPresent;

public class PostWrapper {

    private WebElement cardPost;
    private final By ARROW = By.xpath(".//*[@class='ic12 ic12_arrow-down lp']");
    private final static By TEXT = By.xpath(".//*[@class='media-text_cnt_tx emoji-tx textWrap']");
    private final By MARK = By.xpath(".//*[@class='mlr_top_ac']//*[@class='tico_img ic ic_bookmark-g']");
    private final By CLOSE = By.xpath(".//*[@class='ic media-layer_close_ico']");


    public PostWrapper(WebElement post){
        this.cardPost = post;
    }

    public String getMessage(){
        return cardPost.findElement(TEXT).getText();
    }
    public PostPage click(WebDriver driver){
        cardPost.click();
        return new PostPage(driver);
    }

    public static PostWrapper getPostByText(String message,WebDriver driver, List<PostWrapper> posts) {
        for(PostWrapper card: posts){
            if(!isElementPresent(TEXT,driver)) return null;
            if (card.getMessage().contains(message)) {
                return card;
            }
        }
        return null;
    }
    public BookmarkPage deleteMark( WebDriver driver){
        By delete = By.xpath(".//*[@title='Убрать из закладок']");
        Assert.assertTrue("Нет крестика удаления из закладок",isElementPresent(delete, driver));
        cardPost.findElement(delete).click();
        return new BookmarkPage(driver);
    }
    public PostPage deletePost( WebDriver driver){
        By delete = By.xpath(".//*[@class='feed_close']");
        Assert.assertTrue("Нет крестика удаления поста",isElementPresent(delete, driver));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", cardPost.findElement(delete));
        return new PostPage(driver);
    }
    public PostPage addMark(WebDriver driver){
        Actions actions = new Actions(driver);

        Assert.assertTrue("Нет стрелочки для добавления в закладки",isElementPresent(ARROW, driver));
        actions.moveToElement(driver.findElement(ARROW));

        Assert.assertTrue("Нет добавления в закладки",isElementPresent(MARK, driver));
        WebElement element = driver.findElement(MARK);

        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);

        Assert.assertTrue("Нет кнопки закрытия заметки",isElementPresent(CLOSE, driver));
        driver.findElement(CLOSE).click();
        return new PostPage(driver);
    }

}
