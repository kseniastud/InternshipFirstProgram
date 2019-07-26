package pages;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageAfterLogin {
    private final WebDriver driver;
    private final By buttonLogout = By.xpath("//*[contains(@class ,'mainmenu') and contains(text(),'Выход')]");

    public HomePageAfterLogin(WebDriver driver) {
        this.driver=driver;
    }

    public void logoutAssert(){
        Assert.assertTrue("Вход на сайт не был успешным", driver.findElement(buttonLogout).isDisplayed());

    }

}
