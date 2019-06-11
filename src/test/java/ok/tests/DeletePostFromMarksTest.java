package ok.tests;

import ok.TestBase;
import ok.pages.BookmarkPage;
import ok.pages.post.PostPage;
import ok.pages.post.PostWrapper;
import ok.pages.UserMainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DeletePostFromMarksTest extends TestBase {
    private String message;

    @Before
    public void setUp() throws Exception {
        super.driver = new ChromeDriver();
        message = ":)";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //логинюсь
        UserMainPage userMainPage = login();
        // добавить пост
        PostPage postPage = userMainPage.openPostPage().addPost(message);
        //добавить этот пост в закладки
        PostWrapper postWrapper = postPage.getPostWrapperByText(message);
        Assert.assertNotNull("Не найдено последней заметки", postWrapper);
        postWrapper.click(driver);
        postPage = postWrapper.addMark(driver);
        //открыть закладки и найти эту заметку
        postWrapper = postPage.openBookmark().clickOnPostBookmark().getPostWrapperByText(message);
        // проверка успешности добавления
        Assert.assertNotNull("ПОСТ НЕ БЫЛ ДОБАВЛЕН В ЗАКЛАДКИ", postWrapper);
    }

    /**
     * Добавление заметки в закладки, удаление заметки из закладок со страницы закладок, проверка удаления
     */
    @Test
    public void deletePostTest() throws Exception {
        // удаление из закладок
        BookmarkPage bookMarkPage = new BookmarkPage(driver).getPostWrapperByText(message).deleteMark(driver);
        //обновляю страницу
        driver.navigate().refresh();
        //проверка удаления поста из закладок
        PostWrapper postWrapper = bookMarkPage.getPostWrapperByText(message);
        Assert.assertNull("ПОСТ НЕ БЫЛ УДАЛЁН ИЗ ЗАКЛАДОК", postWrapper);
    }

    @After
    public void deletePost() {
        driver.get(baseUrl + "/feed");
        //открываю заметки
        UserMainPage userMainPage = new UserMainPage(driver);
        PostPage postPage = userMainPage.openPostPage();
        //удаление добавленной заметки
        PostWrapper postWrapper = postPage.getPostWrapperByText(message);
        postWrapper.deletePost(driver);
    }

}