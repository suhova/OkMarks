package ok;

import ok.pages.*;
import ok.tests.TestBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ok.pages.BasePage.openMark;
import static ok.pages.CardTransformer.wrapMark;
import static ok.pages.CardTransformer.wrapMenu;
import static ok.pages.MarkWrapper.checkMark;
import static ok.pages.MenuWrapper.clickByName;
import static org.junit.Assert.fail;

public class GroupTest extends TestBase {
    private String baseUrl;
    private String login;
    private String password;
    private String groupName;
    private By MARKS;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        MARKS = By.xpath(".//*[@class='cardsList_li show-on-hover posR']");
        driver = new ChromeDriver();
        baseUrl = "https://ok.ru/";
        login = "technopolisBot206";
        password = "technopolis16";
        groupName = "Технополис OK";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void groupTest1() throws Exception {

        MarkWrapper bookmark = groupTesting(driver, login,password,groupName,baseUrl);

// перехожу в группу и удалаю из закладок
        bookmark.click();
        GroupPage groupPage = new GroupPage(driver);
        groupPage.addMark();
//открываю закладки
        openMark(driver);
//проверка
        List<MarkWrapper> marks2 = wrapMark(driver.findElements(MARKS));
        Assert.assertNull("ГРУППА НЕ БЫЛА УДАЛЕНА ИЗ ЗАКЛАДОК СО СТРАНИЦЫ ГРУППЫ", checkMark(groupName, marks2));
    }

    @Test
    public void groupTest2() throws Exception {

        MarkWrapper bookmark = groupTesting(driver, login,password,groupName,baseUrl);

// удалаю из закладок со страницы закладок
        bookmark.deleteMark();
//обновляю страницу
        driver.navigate().refresh();
//проверка
        List<MarkWrapper> marks2 = wrapMark(driver.findElements(MARKS));
        Assert.assertNull("ГРУППА НЕ БЫЛА УДАЛЕНА ИЗ ЗАКЛАДОК СО СТРАНИЦЫ ГРУППЫ", checkMark(groupName, marks2));

    }

    private MarkWrapper groupTesting(WebDriver driver, String login, String password, String groupName, String baseUrl) {
//логинюсь
        driver.get(baseUrl + "/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginEnter(login);
        loginPage.passwordEnter(password);
        UserMainPage userMainPage = loginPage.clickEnter();
// перехожу в группы
        final By MENU = By.xpath(".//*[@id='hook_Block_Navigation']//*[@class='nav-side_i  __with-ic']");
        List<MenuWrapper> menu = wrapMenu(driver.findElements(MENU));
        Assert.assertTrue("Нет такого элемента меню", clickByName("Группы", menu));
        GroupsPage groupsPage = new GroupsPage(driver);
//перехожу в группу ТП
        GroupPage groupPage = groupsPage.open(groupName);
//добавляю группу в закладки
        if (groupPage.markStatus()) {
            groupPage.addMark();
        }
//открываю закладки
        openMark(driver);

        List<MarkWrapper> marks = wrapMark(driver.findElements(MARKS));
//ищу группу с нужным названием
        MarkWrapper bookmark = checkMark(groupName, marks);
//проверка
        Assert.assertNotNull("ГРУППА НЕ ДОБАВЛЕНА В ЗАКЛАДКИ", bookmark);
        return bookmark;
    }



        @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}

