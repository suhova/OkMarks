package ok;

import ok.pages.BookmarkPage;
import ok.pages.PostPage;
import ok.pages.PostWrapper;
import ok.pages.UserMainPage;
import ok.tests.TestBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DeletePostFromMarksTest extends TestBase {
    private  String message;

    @Before
    public void setUp() throws Exception {
        super.driver = new ChromeDriver();
        message = ":)";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     * Добавление заметки в закладки, удаление заметки из закладок со страницы закладок, проверка удаления
     */
    @Test
    public void deletePostTest() throws Exception {
        //логинюсь
        UserMainPage userMainPage = login();
        PostPage postPage = userMainPage.openPostPage();
        // добавить пост
        postPage.addPost(message);
        PostWrapper postWrapper = postPage.getPostWrapperByText(message);
        Assert.assertNotNull("Не найдено последней заметки", postWrapper);
        postWrapper.click(driver);
        //добавить этот пост в закладки
        postWrapper.addMark(driver);
        //открыть закладки
        BookmarkPage bookMarkPage = postPage.openMark();
        bookMarkPage.clickOnPostMarks();
        postWrapper = bookMarkPage.getPostWrapperByText(message);
        // проверка успешности добавления
        Assert.assertNotNull("ПОСТ НЕ БЫЛ ДОБАВЛЕН В ЗАКЛАДКИ",postWrapper);

        // удаление из закладок
        bookMarkPage = postWrapper.deleteMark(driver);
        //обновляю страницу
        driver.navigate().refresh();
        //проверка удаления поста из закладок
        postWrapper = bookMarkPage.getPostWrapperByText(message);
        Assert.assertNull("ПОСТ НЕ БЫЛ УДАЛЁН ИЗ ЗАКЛАДОК",postWrapper);
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
