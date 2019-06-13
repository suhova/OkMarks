package ok.pages.bookmark;

import ok.pages.UserPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UserBookmarkWrapper {
    private WebElement cardMark;
    private final By DELETE = By.xpath(".//*[@title='Убрать из закладок']");
    private final By NAME = By.xpath(".//*[@class='ellip']");

    public UserBookmarkWrapper(WebElement mark) {
        this.cardMark = mark;
    }

    public String getHref() {
        return cardMark.findElement(NAME).findElement(By.xpath(".//*[@class='o']")).getAttribute("href");
    }

    public UserPage click(WebDriver driver) {
        cardMark.click();
        return new UserPage(driver);
    }

    //вернуть юзера по ссылке на его страницу
    public static UserBookmarkWrapper getUserBookmarkByHref(String href, List<UserBookmarkWrapper> marks) {
        for (UserBookmarkWrapper card : marks) {
            if (card.getHref().contains(href)) {
                return card;
            }
        }
        return null;
    }

    //удалить юзера из заметок
    public BookmarkPage deleteMark(WebDriver driver) {
        cardMark.findElement(DELETE).click();
        return new BookmarkPage(driver);
    }
}
