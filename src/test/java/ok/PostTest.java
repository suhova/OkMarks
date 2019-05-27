package ok;

import ok.pages.LoginPage;
import ok.pages.MenuWrapper;
import ok.pages.PostPage;
import ok.pages.UserMainPage;
import ok.tests.TestBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static ok.pages.CardTransformer.wrapMenu;
import static ok.pages.MenuWrapper.clickByName;
import static org.junit.Assert.fail;

public class PostTest extends TestBase {
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
    public void postTest() throws Exception {
//логинюсь
        driver.get(baseUrl + "/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginEnter(login);
        loginPage.passwordEnter(password);
        UserMainPage userMainPage = loginPage.clickEnter();
// перехожу в Заметки
        final By MENU = By.xpath(".//*[@id='hook_Block_Navigation']//*[@class='nav-side_i  __with-ic']");
        List<MenuWrapper> menu = wrapMenu(driver.findElements(MENU));
        Assert.assertTrue("Нет такого элемента меню", clickByName("Заметки", menu));
        PostPage postPage = new PostPage(driver);


      //  postPage.addPost();
        postPage.addMark();


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


