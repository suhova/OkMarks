package ok.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MenuWrapper {
    private final WebElement cardMenu;

    public MenuWrapper(WebElement cardMenu){
        this.cardMenu = cardMenu;
    }

    public String getName(){
        return cardMenu.findElement(By.xpath(".//*[@class='tico_txt']")).getText();
    }

    public void click(){
        cardMenu.click();
    }

    // кликнуть на указанный пункт меню (Группы/Закладки/Друзья)
    public static boolean clickByName(String name, List<MenuWrapper> menu) {
        for(MenuWrapper card: menu){
            if (card.getName().equals(name)) {
                card.click();
                return true;
            }
        }
        return false;
    }
}
