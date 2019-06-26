package ok.pages.group;

import ok.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupPage extends BasePage {
    private final By NAVIGATION_TOOLBAR = By.xpath("//*[@class='toolbar_c']");
    // ЭТО ОЧЕН-ОЧЕНЬ ПЛОХО)))0)
    private final By BOOKMARK = By.id("hook_Block_inplrmbm_TopCard_53245288710321_4");
    private final By HEADER = By.id("hook_Block_MainContentHeader");
    private final By GROUP_MENU = By.id("hook_Block_AltGroupMainMenu");
    private final By MAIN_BLOCK = By.xpath(".//*[@class='group-feed']");


    public GroupPage(WebDriver driver) {
        BasePage.driver = driver;
        check(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались шапки группы",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(HEADER), 10, 500));
        Assert.assertTrue("Не дождались блока с контентом",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MAIN_BLOCK), 10, 500));
        Assert.assertTrue("Не дождались блока меню групп",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(GROUP_MENU), 10, 500));
        Assert.assertTrue("Не дождались блока добавления в закладки",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(BOOKMARK), 10, 500));
        Assert.assertTrue("Не дождались блока меню сверху",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(NAVIGATION_TOOLBAR), 10, 500));
    }

    //добавить группу в заметки
    public GroupPage addBookmark() {
        Assert.assertTrue("Нет поля добавления в закладки", isElementPresent(BOOKMARK));
        driver.findElement(BOOKMARK).click();
        return this;
    }

    // проверить находится ли группа в закладках
    public boolean bookmarkStatus() {
        Assert.assertTrue("Нет поля добавления в закладки", isElementPresent(BOOKMARK));
        String status = driver.findElement(BOOKMARK).getText();
        return status.equals("В закладки");
    }

}
