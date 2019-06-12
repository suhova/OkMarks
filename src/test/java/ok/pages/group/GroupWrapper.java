package ok.pages.group;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static ok.pages.Helper.isElementVisible;

public class GroupWrapper {

    private final By PHOTO = By.xpath(".//*[@class='photo']");
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

    //получить группу по названию
    public static GroupWrapper getGroupByName(String name, List<GroupWrapper> groups) {
        for(GroupWrapper card: groups){
            if (card.getName().contains(name)) {
                return card;
            }
        }
        return null;
    }
    //открыть группу
    public GroupPage openGroup(WebDriver driver){
        Assert.assertTrue("Нет фото группы",isElementVisible(PHOTO,driver));
        cardGroup.findElement(PHOTO).click();
        return new GroupPage(driver);
    }

}
