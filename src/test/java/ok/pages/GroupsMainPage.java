package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ok.pages.CardTransformer.wrapGroup;
import static ok.pages.GroupWrapper.getGroupByName;

public class GroupsMainPage extends BasePage{
    private final By MENU_TOP = By.id("mainTopContentRow");
    private final By POPULAR = By.id("hook_Block_PopularGroupsListBlock");
    private final By MENU_LEFT = By.id("hook_Block_UserGroupsCatalogRBlock");
    private final By SEARCH = By.id("query_userAltGroupSearch");


    public GroupsMainPage(WebDriver driver) {
        super.driver = driver;
        check(driver);
    }

    @Override
    void check(WebDriver driver) {
        Assert.assertTrue("Не дождались блока меню сверху",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU_TOP), 10, 500));

        Assert.assertTrue("Не дождались блока популярное",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(POPULAR), 10, 500));

        Assert.assertTrue("Не дождались блока меню слева",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU_LEFT), 10, 500));

        Assert.assertTrue("Не дождались блока поиска групп",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(SEARCH), 10, 500));
    }

    public GroupsMainPage searchGroup (String name) {
        Assert.assertTrue("Нет поля поиска групп",isElementPresent(SEARCH));
        driver.findElement(SEARCH).clear();
        driver.findElement(SEARCH).sendKeys(name);

        return this;
    }

    public GroupPage openGroupByName(String groupName){
        //ввод в поиск названия группы
        searchGroup(groupName);
        By GROUPS_BLOCK = By.id("userGroupsSearchResultList");
        Assert.assertTrue("Нет списка групп",isElementPresent(GROUPS_BLOCK));
        List<GroupWrapper> groups = wrapGroup(driver.findElements(GROUPS_BLOCK));
        GroupWrapper groupTP = getGroupByName(groupName,groups);
        Assert.assertNotNull("Не найдена группа c таким именем",groupTP);
        return groupTP.openGroup(driver);
    }
}


