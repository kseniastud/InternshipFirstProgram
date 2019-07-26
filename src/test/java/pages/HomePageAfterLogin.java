package pages;

import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageAfterLogin {
    private WebDriver driver;
    By buttonLogout = By.xpath("//*[contains(@class ,'mainmenu') and contains(text(),'Выход')]");

    By searchInputField = By.xpath("//input[@name='nm']");

    By buttonSearch = By.xpath("search_submit");

    public static void main(String[] args) {
    }

    public HomePageAfterLogin(WebDriver driver) {
        this.driver=driver;
    }

    public HomePageAfterLogin logoutAssert(){
        Assert.assertTrue("Вход на сайт не был успешным", driver.findElement(buttonLogout).isDisplayed());
        return this;
    }

}
