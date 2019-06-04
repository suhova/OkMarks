package ok;

import ok.pages.*;
import ok.tests.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static ok.pages.BasePage.openMark;

public class RemoveGroupFromMarksByGroupPageTest extends TestBase {
    private String groupName;

    @Before
    public void setUp() throws Exception {
        super.driver = new ChromeDriver();
        groupName = "Технополис OK";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    /**
     * Добавление группы в закладки, удаление из закладкок со страницы Групп
     */
    @Test
    public void removeGroupFromMarksByGroupPageTest() throws Exception {
        //логинюсь
        UserMainPage userMainPage = login();
        // перехожу в группы
       GroupsMainPage groupsPage = userMainPage.openGroupsMainPage();
       //открываю группу ТП
        GroupPage groupPage = groupsPage.openGroupByName(groupName);
        //добавляю группу в закладки
        if (groupPage.markStatus()) {
            groupPage.addMark();
        }
        //открываю закладки
        BookmarkPage bookmarkPage = openMark();
        Assert.assertFalse("ГРУППА НЕ ДОБАВЛЕНА В ЗАКЛАДКИ",bookmarkPage.isEmptyMarkBlock());
        //ищу группу с нужным названием
        GroupMarkWrapper groupMarkWrapper = bookmarkPage.getGroupMarkWrapper(groupName);
       //проверка
        Assert.assertNotNull("ГРУППА НЕ ДОБАВЛЕНА В ЗАКЛАДКИ", groupMarkWrapper);

       // перехожу в группу и удалаю из закладок
        groupMarkWrapper.click();
        groupPage = new GroupPage(driver);
        groupPage.addMark();
        //открываю закладки
        bookmarkPage = openMark();
        bookmarkPage.clickOnGroupMarks();

        //проверка (тест не упадёт, если блок закладок пустой, либо если нужной группы нет в закладках)
        if(!bookmarkPage.isEmptyMarkBlock()) {
            groupMarkWrapper = bookmarkPage.getGroupMarkWrapper(groupName);
            Assert.assertNull("ГРУППА НЕ БЫЛА УДАЛЕНА ИЗ ЗАКЛАДОК СО СТРАНИЦЫ ГРУППЫ", groupMarkWrapper);
        }
    }
}
