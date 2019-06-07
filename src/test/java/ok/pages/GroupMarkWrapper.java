package ok.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupMarkWrapper {
    private WebElement cardMark;

    public GroupMarkWrapper(WebElement mark){
        this.cardMark = mark;
    }

    public String getName(){
        return cardMark.findElement(By.xpath(".//*[@class='o two-lines']")).getText();
    }
    public void click(){
        cardMark.click();
    }
    //вернуть группу по названию
    public static GroupMarkWrapper getMarkByName(String name, List<GroupMarkWrapper> marks) {
        for(GroupMarkWrapper card: marks){
            if (card.getName().contains(name)) {
                return card;
            }
        }
        return null;
    }
    //удалить группу из заметок
    public BookmarkPage deleteMark( WebDriver driver){
        cardMark.findElement(By.xpath(".//*[@title='Убрать из закладок']")).click();
        return new BookmarkPage(driver);
    }
}
