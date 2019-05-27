package ok.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    private static final By MENU = By.xpath(".//*[@id='hook_Block_ToolbarUserDropdown']//*[@class='toolbar_nav_a js-toolbar-menu']");
    private static final By MARK = By.xpath(".//*[contains(text(),'Закладки')]");
    private static final By MY_PAGE = By.xpath(".//*[@class='nav-side __navigation']//*[contains(text(),'Лента')]");
    abstract void check(WebDriver driver);

    public static UserMainPage openMyPage(WebDriver driver) {
        driver.findElement(MENU).click();
        driver.findElement(MY_PAGE).click();
        return new UserMainPage(driver);
    }

    public static BookmarkPage openMark(WebDriver driver) {
        driver.findElement(MENU).click();
        driver.findElement(MARK).click();
        return new BookmarkPage(driver);
    }
}
