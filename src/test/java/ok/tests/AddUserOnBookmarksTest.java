package ok.tests;

import ok.TestBase;
import ok.pages.UserPage;
import ok.pages.bookmark.BookmarkPage;
import ok.pages.bookmark.UserBookmarkWrapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddUserOnBookmarksTest extends TestBase {

    private String userUrl;

    @Before
    public void start() throws Exception {
        userUrl = "/profile/578527183595";
        //логинюсь
        login();
        //открываю страницу юзера
        driver.get(baseUrl + userUrl);
        //добавляю его в закладки
        UserPage userPage = new UserPage(driver).addUserOnMarks();
        //открываю закладки
        BookmarkPage bookmarkPage = userPage.openBookmark().clickOnUserBookmark();
        UserBookmarkWrapper userBookmarkWrapper = bookmarkPage.getUserBookmarkWrapperByHref(userUrl);
        Assert.assertNotNull("ПОЛЬЗОВАТЕЛЬ НЕ БЫЛ ДОБАВЛЕН В ЗАКЛАДКИ", userBookmarkWrapper);
    }

    /**
     * Добавление юзера в закладки, проверка, что кнопка Закладки сменилась на Убрать из закладок
     */
    @Test
    public void addUserOnBookmarks() throws Exception {
        //открываю страницу юзера
        driver.get(baseUrl + userUrl);
        String buttonText = new UserPage(driver).getTextFromButtonAddOnBookmarks();
//имеет смысл слова "Убрать из закладок" хранить в пейдже UserPage => проверку делать там же, возвращать boolean
        Assert.assertTrue("ТЕКСТ НА КНОПКЕ НЕКОРРЕКТНЫЙ", buttonText.contains("Убрать из закладок"));
    }

    @After
    public void deleteUserFromMarks() throws Exception {
        driver.get(baseUrl + "/bookmarks/users");
        //удаляю пользователя из закладок
        UserBookmarkWrapper userBookmarkWrapper = new BookmarkPage(driver).getUserBookmarkWrapperByHref(userUrl);
        if (userBookmarkWrapper != null) {
            userBookmarkWrapper.deleteMark(driver);
        }
    }

}