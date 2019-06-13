package ok.pages.mypage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MenuWrapper {

    private WebElement cardMenu;

    public MenuWrapper(WebElement cardMenu) {
        this.cardMenu = cardMenu;
    }

    public String getName() {
        return cardMenu.findElement(By.xpath(".//*[@class='tico_txt']")).getText();
    }

    public void click() {
        cardMenu.click();
    }
}
