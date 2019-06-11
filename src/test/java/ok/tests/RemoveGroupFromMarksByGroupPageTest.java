package ok.tests;

import ok.TestBase;
import ok.pages.BookmarkPage;
import ok.pages.UserMainPage;
import ok.pages.group.GroupPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class RemoveGroupFromMarksByGroupPageTest extends TestBase {
    private String groupName;

    @Before
    public void start() throws Exception {
        groupName = "Технополис OK";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //логинюсь
        UserMainPage userMainPage = login();
        // перехожу в группы и открываю группу ТП
        GroupPage groupPage = userMainPage.openGroupsMainPage().openGroupByName(groupName);
        //добавляю группу в закладки
        if (groupPage.bookmarkStatus()) {
            groupPage = groupPage.addBookmark();
        }
        //открываю закладки
        BookmarkPage bookmarkPage = groupPage.openBookmark();
        Assert.assertFalse("ГРУППА НЕ ДОБАВЛЕНА В ЗАКЛАДКИ", bookmarkPage.isEmptyMarkBlock());
    }

    /**
     * Добавление группы в закладки, удаление из закладкок со страницы Групп
     */
    @Test
    public void removeGroupFromMarksByGroupPageTest() throws Exception {
        driver.get(baseUrl + "/bookmarks");
        BookmarkPage bookmarkPage = new BookmarkPage(driver);
        //ищу группу с нужным названием, перехожу в неё и удалаю из закладок
        GroupPage groupPage = bookmarkPage.getGroupMarkWrapper(groupName).click(driver).addBookmark();
        //открываю закладки
        bookmarkPage = groupPage.openBookmark().clickOnGroupBookmark();
        //проверка наличия пустого блока
        Assert.assertTrue("ГРУППА НЕ БЫЛА УДАЛЕНА ИЗ ЗАКЛАДОК СО СТРАНИЦЫ ГРУППЫ", bookmarkPage.isEmptyMarkBlock());
    }
}

