package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UserPage extends BasePage {
    private final By NAVIGATION_TOOLBAR = By.xpath("//*[@class='toolbar_c']");
    private final By AVATAR = By.id("hook_Block_Avatar");
    private final By MENU = By.xpath(".//*[@class='main-content-header_data_main-menu']");
    private final By ROUND_SELECTOR_ON_MENU = By.xpath(".//*[@class='u-menu_a toggle-dropdown']");
    private final By ADD_ON_BOOKMARKS = By.xpath(".//*[@class='svg-ic svg-ico_bookmark_16 null']");
    private final By DELETE_FROM_BOOKMARKS = By.xpath(".//*[@class='svg-ic svg-ico_bookmark_off_16 null']");
    private final By BOOKMARK = By.xpath("//*[@class='u-menu_li __hl __custom']//*[contains(text(),'аклад')]");


    public UserPage(WebDriver driver) {
        super.driver = driver;
        check(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались меню добавить в друзья, написать, подарок",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU), 10, 500));
        Assert.assertTrue("Не дождались блока навигациии",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(NAVIGATION_TOOLBAR), 10, 500));
        Assert.assertTrue("Не дождались аватара",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(AVATAR), 10, 500));
    }

    public UserPage addUserOnMarks() {
        Assert.assertTrue("Нет круглой кнопки для добавления в закладки в меню", isElementVisible(ROUND_SELECTOR_ON_MENU));
        driver.findElement(ROUND_SELECTOR_ON_MENU).click();
        if (isElementVisible(ADD_ON_BOOKMARKS)) {
            driver.findElement(ADD_ON_BOOKMARKS).click();
            return this;
        } else if (isElementVisible(DELETE_FROM_BOOKMARKS)) {
            return this;
        }
        Assert.fail("Нет кнопки добавления/удаления юзера из закладок");
        return this;
    }

    public String getTextFromButtonAddOnBookmarks() {
        Assert.assertTrue("Нет круглой кнопки для добавления в закладки в меню", isElementVisible(ROUND_SELECTOR_ON_MENU));
        driver.findElement(ROUND_SELECTOR_ON_MENU).click();
        Assert.assertTrue("Нет кнопки добавления/удаления юзера из закладок", isElementVisible(BOOKMARK));
        return driver.findElement(BOOKMARK).getText();
    }
}
