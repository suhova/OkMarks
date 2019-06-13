package ok.tests;

import ok.TestBase;
import ok.pages.bookmark.BookmarkPage;
import ok.pages.mypage.MyUserPage;
import ok.pages.post.PostPage;
import ok.pages.post.PostWrapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeletePostFromMarksTest extends TestBase {
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
        BookmarkPage bookmarkPage = postPage.openBookmark();
        bookmarkPage = bookmarkPage.clickOnPostBookmark();
        // проверка наличия поста в закладках
        postWrapper = bookmarkPage.getPostWrapperByText(message);
        Assert.assertNotNull("ПОСТ НЕ ДОБАВЛЕН В ЗАКЛАДКИ", postWrapper);
    }

    /**
     * Добавление заметки в закладки, удаление заметки из закладок со страницы закладок, проверка удаления
     */
    @Test
    public void deletePostTest() throws Exception {
        // удаление из закладок
        new BookmarkPage(driver).getPostWrapperByText(message).deleteMark(driver);
        //обновляю страницу
        driver.navigate().refresh();
        BookmarkPage bookmarkPage = new BookmarkPage(driver).clickOnPostBookmark();
        //проверка удаления поста из закладок
//        Assert.assertTrue("ПОСТ НЕ БЫЛ УДАЛЁН ИЗ ЗАКЛАДОК",bookmarkPage.isEmptyMarkBlock());
        PostWrapper postWrapper = bookmarkPage.getPostWrapperByText(message);
        Assert.assertNull("ПОСТ НЕ БЫЛ УДАЛЁН ИЗ ЗАКЛАДОК", postWrapper);

    }

    @After
    public void deletePost() {
        driver.get(baseUrl + "/feed");
        //открываю заметки
        MyUserPage myUserPage = new MyUserPage(driver);
        PostPage postPage = myUserPage.openPostPage();
        //удаление добавленной заметки
        PostWrapper postWrapper = postPage.getPostWrapperByText(message);
        postWrapper.deletePost(driver);
    }

}
