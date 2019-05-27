package ok.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MarkWrapper {
    private final WebElement cardMark;

    public MarkWrapper(WebElement mark){
        this.cardMark = mark;
    }

    public String getName(){
        return cardMark.findElement(By.xpath(".//*[@class='o two-lines']")).getText();
    }
    public void click(){
        cardMark.click();
    }

    public static MarkWrapper checkMark(String name, List<MarkWrapper> marks) {
        for(MarkWrapper card: marks){
            if (card.getName().contains(name)) {
                return card;
            }
        }
        return null;
    }

    public void deleteMark(){
        cardMark.findElement(By.xpath(".//*[@title='Убрать из закладок']")).click();
    }
}
