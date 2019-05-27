package ok.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Helper {

    public static boolean isElementPresent(By by,WebDriver driver) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
