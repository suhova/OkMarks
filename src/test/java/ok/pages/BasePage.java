package ok.pages;

import com.google.common.base.Preconditions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {
    private static final By MENU = By.xpath(".//*[@id='hook_Block_ToolbarUserDropdown']//*[@class='toolbar_nav_a js-toolbar-menu']");
    private static final By MARK = By.xpath(".//*[contains(text(),'Закладки')]");
    private static final By MY_PAGE = By.xpath(".//*[@class='nav-side __navigation']//*[contains(text(),'Лента')]");

    protected abstract void check(WebDriver driver);

    protected static WebDriver driver;

    //открывает ленту
    public UserMainPage openMyPage() {
        Assert.assertTrue("Не найдено меню", isElementPresent(MENU));
        driver.findElement(MENU).click();
        Assert.assertTrue("Не найдено моей страницы в меню", isElementPresent(MY_PAGE));
        driver.findElement(MY_PAGE).click();
        return new UserMainPage(driver);
    }

    public boolean isElementPresent(By by) {
        try {
            this.driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementVisible(By by) {
        try {
            if(this.driver.findElement(by).isDisplayed()) return true;
            else return false;
        } catch (Exception e) {
            return false;
        }
    }

    //открыть Закладки
    public BookmarkPage openBookmark() {
        Assert.assertTrue("Не найдено меню", isElementVisible(MENU));
        driver.findElement(MENU).click();
        Assert.assertTrue("Не найдено закладок в меню", isElementVisible(MARK));
        driver.findElement(MARK).click();
        return new BookmarkPage(driver);
    }

    public boolean explicitWait(final ExpectedCondition<?> condition, long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkNotNull(condition, "Condition must be not null");
        Preconditions.checkArgument(TimeUnit.MINUTES.toSeconds(3) > maxCheckTimeInSeconds, "Max check time in seconds should be less than 3 minutes");
        checkConditionTimeouts(maxCheckTimeInSeconds, millisecondsBetweenChecks);
        try {
            // сбрасываем ожидания в 0
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            // создаем эксплицитное ожидание
            WebDriverWait explicitWait = new WebDriverWait(driver, maxCheckTimeInSeconds, millisecondsBetweenChecks);
            // проверяем его
            explicitWait.until(condition);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            // при любом результате восстанавливаем значение имплицитного ожидания по умолчанию
            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } else {
                throw new IllegalArgumentException("Driver shouldnt be null");
            }
        }
    }

    private void checkConditionTimeouts(long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkState(maxCheckTimeInSeconds > 0, "maximum check time in seconds must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks > 0, "milliseconds count between checks must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks < (maxCheckTimeInSeconds * 1000),
                "Millis between checks must be less than max seconds to wait");
    }
}
