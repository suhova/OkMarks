package ok.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static ok.pages.CardTransformer.wrapMark;
import static ok.pages.CardTransformer.wrapPost;
import static ok.pages.GroupMarkWrapper.getMarkByName;
import static ok.pages.PostWrapper.getPostByText;

public class BookmarkPage extends BasePage {

    private final By MENU_TOP = By.id("mainTopContentRow");
    private final By MENU_LEFT = By.id("hook_Block_BookmarksMenuRB");
    private final By MAIN_BLOCK = By.id("mainContentContentColumn");
    private final By POSTS = By.xpath(".//*[@class='nav-side_i ']//*[contains(text(),'Заметки')]");
    private final By GROUPS = By.xpath(".//*[@class='nav-side_i ']//*[contains(text(),'Группы')]");
    private final By MARKS = By.xpath(".//*[@class='cardsList_li show-on-hover posR']");


    public BookmarkPage(WebDriver driver) {
        super.driver = driver;
        check(driver);
    }

    @Override
    void check(WebDriver driver) {
        Assert.assertTrue("Не дождались блока меню сверху",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU_TOP), 10, 500));
        Assert.assertTrue("Не дождались основного блока закладок",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MAIN_BLOCK), 10, 500));
        Assert.assertTrue("Не дождались блока меню слева",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MENU_LEFT), 10, 500));
    }
    //в заметках кликнуть на заметки Записей
    public BookmarkPage clickOnPostMarks(){
        Assert.assertTrue("Не найдено поля меню Закладки", isElementPresent(POSTS));
        driver.findElement(POSTS).click();
        return new BookmarkPage(driver);
    }
    //в заметках кликнуть на заметки Групп
    public BookmarkPage clickOnGroupMarks(){
        Assert.assertTrue("Не найдено поля меню Группы", isElementPresent(GROUPS));
        driver.findElement(GROUPS).click();
        return new BookmarkPage(driver);
    }
    //получить враппер заметки по содержащемуся в ней сообщению
    public PostWrapper getPostWrapperByText(String message){

        By marksBy = By.xpath(".//*[@class='bk_list bk_list__usertopic  bk_list__page']");
        Assert.assertTrue("Нет списка закладок",isElementPresent(marksBy));

        List<PostWrapper> marks = wrapPost(driver.findElements(marksBy));
        return getPostByText(message,driver,marks);
    }
    // получить враппер группы в закладках по названию группы
    public GroupMarkWrapper getGroupMarkWrapper(String groupName) {
        clickOnGroupMarks();
        Assert.assertTrue("Нет списка закладок",isElementPresent(MARKS));
        List<GroupMarkWrapper> marks = wrapMark(driver.findElements(MARKS));
         return getMarkByName(groupName, marks);
    }
    //проверить наличие блока "У вас пока нет заметок"
    public boolean isEmptyMarkBlock(){
        By emptyMarksBlock = By.xpath(".//*[@class='stub-empty __bookmarks ']");
        if (isElementPresent(emptyMarksBlock))return true;
        return false;
    }

}
