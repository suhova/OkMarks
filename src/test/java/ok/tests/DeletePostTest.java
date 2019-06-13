package ok.tests;

import ok.TestBase;
import ok.pages.bookmark.BookmarkPage;
import ok.pages.mypage.MyUserPage;
import ok.pages.post.PostPage;
import ok.pages.post.PostWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeletePostTest extends TestBase {
    private String message;

    @Before
    public void start() throws Exception {
        message = ":)";
        //логинюсь
        MyUserPage myUserPage = login();
        // добавить пост
        PostPage postPage = myUserPage.openPostPage().deleteAllPosts().addPost(message);
        //добавить этот пост в закладки
        PostWrapper postWrapper = postPage.getPostWrapperByText(message);
        Assert.assertNotNull("Не найдено последней заметки", postWrapper);
        postWrapper.click(driver);
        postPage = postWrapper.addMark(driver);
        //открыть закладки
        BookmarkPage bookmarkPage = postPage.openBookmark().clickOnPostBookmark();
        // проверка наличия поста в закладках
        postWrapper = bookmarkPage.getPostWrapperByText(message);
        Assert.assertNotNull("ПОСТ НЕ ДОБАВЛЕН В ЗАКЛАДКИ", postWrapper);
    }

    /**
     * Добавление заметки в закладки, удаление заметки, проверка удаления из закладок
     */
    @Test
    public void deletePostTest() throws Exception {
        //удаление добавленной заметки
        PostPage postPage = new BookmarkPage(driver).openMyPage().openPostPage().getPostWrapperByText(message).deletePost(driver);
        //открыть закладки
        BookmarkPage bookmarkPage = postPage.openBookmark().clickOnPostBookmark();
        //проверка удаления поста из закладок
        PostWrapper postWrapper = bookmarkPage.getPostWrapperByText(message);
        Assert.assertNull("ПОСТ НЕ БЫЛ УДАЛЁН ИЗ ЗАКЛАДОК", postWrapper);
    }
}


