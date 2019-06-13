package ok.pages.bookmark;

import ok.pages.group.GroupPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupBookmarkWrapper {
    private WebElement cardMark;
    private final By DELETE = By.xpath(".//*[@title='Убрать из закладок']");

    public GroupBookmarkWrapper(WebElement mark) {
        this.cardMark = mark;
    }

    public String getName() {
        return cardMark.findElement(By.xpath(".//*[@class='o two-lines']")).getText();
    }

    public GroupPage click(WebDriver driver) {
        cardMark.click();
        return new GroupPage(driver);
    }

    //вернуть группу по названию
    public static GroupBookmarkWrapper getGroupBookmarkByName(String name, List<GroupBookmarkWrapper> marks) {
        for (GroupBookmarkWrapper card : marks) {
            if (card.getName().contains(name)) {
                return card;
            }
        }
        return null;
    }

    //удалить группу из заметок
    public BookmarkPage deleteMark(WebDriver driver) {
        cardMark.findElement(DELETE).click();
        return new BookmarkPage(driver);
    }
}
