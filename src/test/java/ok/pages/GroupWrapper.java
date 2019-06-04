package ok.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GroupWrapper {

    private WebElement cardGroup;

    public GroupWrapper(WebElement group){
        this.cardGroup = group;
    }

    public String getName(){
        return cardGroup.findElement(By.xpath(".//*[@class='o']")).getText();
    }
    public void click(){
        cardGroup.click();
    }

    public static GroupWrapper getGroupByName(String name, List<GroupWrapper> groups) {
        for(GroupWrapper card: groups){
            if (card.getName().contains(name)) {
                return card;
            }
        }
        return null;
    }
    public GroupPage openGroup(WebDriver driver){
        cardGroup.findElement(By.xpath(".//*[@class='photo']")).click();
        return new GroupPage(driver);
    }

}
