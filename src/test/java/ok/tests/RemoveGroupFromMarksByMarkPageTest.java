package ok.tests;

import ok.TestBase;
import ok.pages.bookmark.BookmarkPage;
import ok.pages.mypage.MyUserPage;
import ok.pages.group.GroupPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RemoveGroupFromMarksByMarkPageTest extends TestBase {
    private String groupName;

    @Before
    public void start() throws Exception {
        groupName = "Технополис OK";
        //логинюсь
        MyUserPage myUserPage = login();
        // перехожу в группы и открываю группу ТП
        GroupPage groupPage = myUserPage.openGroupsMainPage().openGroupByName(groupName);
        //добавляю группу в закладки
        if (groupPage.bookmarkStatus()) {
            groupPage = groupPage.addBookmark();
        }
        //открываю закладки
        BookmarkPage bookmarkPage = groupPage.openBookmark();
        Assert.assertFalse("ГРУППА НЕ ДОБАВЛЕНА В ЗАКЛАДКИ", bookmarkPage.isEmptyMarkBlock());
    }

    /**
     * Добавление группы в закладки, удаление из закладкок со страницы Закладок
     */
    @Test
    public void removeGroupFromMarksByMarkPageTest() throws Exception {
        driver.get(baseUrl + "/bookmarks");
        //ищу группу с нужным названием и удалаю её из закладок со страницы закладок
        BookmarkPage bookmarkPage = new BookmarkPage(driver).getGroupMarkWrapper(groupName).deleteMark(driver);
        //обновляю страницу
        driver.navigate().refresh();
        //проверка наличия пустого блока
        Assert.assertTrue("ГРУППА НЕ БЫЛА УДАЛЕНА ИЗ ЗАКЛАДОК СО СТРАНИЦЫ ГРУППЫ", bookmarkPage.isEmptyMarkBlock());
    }

}

