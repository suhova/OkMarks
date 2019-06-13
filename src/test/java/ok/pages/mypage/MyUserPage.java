package ok.pages.mypage;

import ok.pages.BasePage;
import ok.pages.group.GroupsMainPage;
import ok.pages.post.PostPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ok.pages.CardTransformer.wrap;

public class MyUserPage extends BasePage {
    private final By NAVIGATION_TOOLBAR = By.xpath("//*[@class='toolbar_c']");
    private final By MAIN_CONTENT = By.id("hook_Block_UserMainFullMRB");
    private final By MENU_LEFT = By.id("hook_Block_SideNavigation");
    private final By AVATAR = By.id("hook_Block_Avatar");
    private final By MENU = By.xpath(".//*[@id='hook_Block_Navigation']//*[@class='nav-side_i  __with-ic']");

    public MyUserPage(WebDriver driver) {
        super.driver = driver;
        check(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались основаного блока",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MAIN_CONTENT), 10, 500));
        Assert.assertTrue("Не дождались блока навигациии",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(NAVIGATION_TOOLBAR), 10, 500));
        Assert.assertTrue("Не дождались аватара",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(AVATAR), 10, 500));
        Assert.assertTrue("Не дождались блока меню",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU_LEFT), 10, 500));
    }

    // Открыть закладки
    public PostPage openPostPage() {
        List<MenuWrapper> menu = wrap(driver.findElements(MENU), MenuWrapper.class);
        Assert.assertTrue("Нет такого элемента меню", clickByName("Заметки", menu));
        return new PostPage(driver);
    }

    // Открыть группы
    public GroupsMainPage openGroupsMainPage() {
        List<MenuWrapper> menu = wrap(driver.findElements(MENU), MenuWrapper.class);
        Assert.assertTrue("Нет такого элемента меню", clickByName("Группы", menu));
        return new GroupsMainPage(driver);
    }

    // кликнуть на указанный пункт меню (Группы/Закладки/Друзья)
    public boolean clickByName(String name, List<MenuWrapper> menu) {
        for (MenuWrapper card : menu) {
            if (card.getName().equals(name)) {
                card.click();
                return true;
            }
        }
        return false;
    }
}
