package ok.pages.bookmark;

import ok.pages.BasePage;
import ok.pages.post.PostWrapper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ok.pages.CardTransformer.*;
import static ok.pages.bookmark.GroupBookmarkWrapper.getGroupBookmarkByName;
import static ok.pages.bookmark.UserBookmarkWrapper.getUserBookmarkByHref;
import static ok.pages.post.PostWrapper.getPostByText;

public class BookmarkPage extends BasePage {

    private final By MENU_TOP = By.id("mainTopContentRow");
    private final By MENU_LEFT = By.id("hook_Block_BookmarksMenuRB");
    private final By MAIN_BLOCK = By.id("mainContentContentColumn");
    private final By POSTS = By.xpath(".//*[contains(@href,'/bookmarks/notes')]");
    private final By USERS = By.xpath(".//*[@class='nav-side_i ']//*[contains(text(),'Люди')]");
    private final By GROUPS = By.xpath(".//*[@class='nav-side_i ']//*[contains(text(),'Группы')]");
    private final By MARKS = By.xpath(".//*[@class='cardsList_li show-on-hover posR']");
    private final By LIST_POST_BOOKMARK = By.xpath(".//*[@class='bk_list bk_list__usertopic  bk_list__page']");
    private final By LIST_USER_BOOKMARK = By.xpath(".//*[@class='bk_list bk_list__user  bk_list__page']");


    public BookmarkPage(WebDriver driver) {
        BasePage.driver = driver;
        check(driver);
    }

    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались блока меню сверху",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU_TOP), 10, 500));
        Assert.assertTrue("Не дождались основного блока закладок",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MAIN_BLOCK), 10, 500));
        Assert.assertTrue("Не дождались блока меню слева",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU_LEFT), 10, 500));
    }

    //в заметках кликнуть на заметки Записей
    public BookmarkPage clickOnPostBookmark() {
        Assert.assertTrue("Не найдено поля меню Заметки", isElementVisible(POSTS));
        driver.findElement(POSTS).click();
        return new BookmarkPage(driver);
    }

    //в заметках кликнуть на заметки Групп
    public BookmarkPage clickOnGroupBookmark() {
        Assert.assertTrue("Не найдено поля меню Группы", isElementVisible(GROUPS));
        driver.findElement(GROUPS).click();
        return new BookmarkPage(driver);
    }

    //в заметках кликнуть на заметки Групп
    public BookmarkPage clickOnUserBookmark() {
        Assert.assertTrue("Не найдено поля меню Люди", isElementVisible(USERS));
        driver.findElement(USERS).click();
        return new BookmarkPage(driver);
    }

    //получить враппер юзера по ссылке на друга
    public UserBookmarkWrapper getUserBookmarkWrapperByHref(String href) {
        Assert.assertTrue("Нет списка закладок", isElementPresent(LIST_USER_BOOKMARK));
        List<UserBookmarkWrapper> marks = wrap(driver.findElements(LIST_USER_BOOKMARK), UserBookmarkWrapper.class);
        return getUserBookmarkByHref(href, marks);
    }

    //получить враппер заметки по содержащемуся в ней сообщению
    public PostWrapper getPostWrapperByText(String message) {
        Assert.assertTrue("Нет списка закладок", isElementPresent(LIST_POST_BOOKMARK));
        List<PostWrapper> marks = wrap(driver.findElements(LIST_POST_BOOKMARK), PostWrapper.class);
        return getPostByText(message, driver, marks);
    }

    // получить враппер группы в закладках по названию группы
    public GroupBookmarkWrapper getGroupMarkWrapper(String groupName) {
        clickOnGroupBookmark();
        Assert.assertTrue("Нет списка закладок", isElementPresent(MARKS));
        List<GroupBookmarkWrapper> marks = wrap(driver.findElements(MARKS), GroupBookmarkWrapper.class);
        return getGroupBookmarkByName(groupName, marks);
    }

    //проверить наличие блока "У вас пока нет заметок"
    public boolean isEmptyMarkBlock() {
        By emptyMarksBlock = By.xpath(".//*[@class='stub-empty __bookmarks ']");
        return isElementPresent(emptyMarksBlock);
    }

}
