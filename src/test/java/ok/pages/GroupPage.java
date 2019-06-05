package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GroupPage extends BasePage{
    private final By NAVIGATION_TOOLBAR = By.xpath("//*[@class='toolbar_c']");
    private final By MARK = By.id("hook_Block_inplrmbm_TopCard_53245288710321_4");
    private final By HEADER = By.id("hook_Block_MainContentHeader");
    private final By GROUP_MENU = By.id("hook_Block_AltGroupMainMenu");
    private final By MAIN_BLOCK = By.xpath(".//*[@class='group-feed']");


    public GroupPage(WebDriver driver) {
        super.driver = driver;
        check(driver);
    }

    @Override
    void check(WebDriver driver) {
        Assert.assertTrue("Не дождались шапки группы",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(HEADER), 10, 500));
        Assert.assertTrue("Не дождались блока с контентом",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MAIN_BLOCK), 10, 500));
        Assert.assertTrue("Не дождались блока меню групп",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(GROUP_MENU), 10, 500));
        Assert.assertTrue("Не дождались блока добавления в закладки",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MARK), 10, 500));
        Assert.assertTrue("Не дождались блока меню сверху",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(NAVIGATION_TOOLBAR), 10, 500));
    }

    public GroupPage addMark() {
        Assert.assertTrue("Нет поля добавления в закладки",isElementPresent(MARK));
        driver.findElement(MARK).click();
        return this;
    }

    public boolean markStatus() {
        Assert.assertTrue("Нет поля добавления в закладки",isElementPresent(MARK));
        String status = driver.findElement(MARK).getText();
            if (status.equals("В закладки")){
                return true;
            }
        return false;
    }

}
