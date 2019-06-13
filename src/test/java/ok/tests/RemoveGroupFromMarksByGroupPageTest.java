package ok.tests;

import ok.TestBase;
import ok.pages.bookmark.BookmarkPage;
import ok.pages.mypage.MyUserPage;
import ok.pages.group.GroupPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RemoveGroupFromMarksByGroupPageTest extends TestBase {
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

