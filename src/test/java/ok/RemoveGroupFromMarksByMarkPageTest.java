package ok;

import ok.pages.*;
import ok.tests.TestBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class RemoveGroupFromMarksByMarkPageTest extends TestBase {
    private String groupName;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        super.driver = driver;
        groupName = "Технополис OK";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * Добавление группы в закладки, удаление из закладкок со страницы Закладок
     */
    @Test
    public void removeGroupFromMarksByMarkPageTest() throws Exception {

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
        BookmarkPage bookmarkPage = groupPage.openMark();
        Assert.assertFalse("ГРУППА НЕ ДОБАВЛЕНА В ЗАКЛАДКИ",bookmarkPage.isEmptyMarkBlock() );
        //ищу группу с нужным названием
        GroupMarkWrapper groupMarkWrapper = bookmarkPage.getGroupMarkWrapper(groupName);
        //проверка
        Assert.assertNotNull("ГРУППА НЕ ДОБАВЛЕНА В ЗАКЛАДКИ", groupMarkWrapper);


        // удалаю из закладок со страницы закладок
        groupMarkWrapper.deleteMark(driver);
        //обновляю страницу
        driver.navigate().refresh();

        //проверка (тест не упадёт, если блок закладок пустой, либо если нужной группы нет в закладках)
        if(!bookmarkPage.isEmptyMarkBlock()) {
            groupMarkWrapper = bookmarkPage.getGroupMarkWrapper(groupName);
            Assert.assertNull("ГРУППА НЕ БЫЛА УДАЛЕНА ИЗ ЗАКЛАДОК СО СТРАНИЦЫ ГРУППЫ", groupMarkWrapper);
        }

    }

}

