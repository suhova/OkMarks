package ok;

import ok.pages.LoginPage;
import ok.pages.mypage.MyUserPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TestBase {
    protected String baseUrl = "https://ok.ru/";
    protected String login = "technopolisBot206";
    protected String password = "technopolis16";
    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    protected MyUserPage login() {
        driver.get(baseUrl + "/dk?st.cmd=anonymMain&st.layer.cmd=PopLayerClose");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginEnter(login);
        loginPage.passwordEnter(password);
        return loginPage.clickEnter();
    }

    private StringBuffer verificationErrors = new StringBuffer();

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}