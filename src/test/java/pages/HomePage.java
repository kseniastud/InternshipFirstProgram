package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    By buttonAuthorization = By.xpath("//*[contains(@class ,'mainmenu') and contains(text(),'Вход')]");

    public HomePage(WebDriver driver) {
        this.driver=driver;
    }
    public LoginPage loginEnter(){
        driver.findElement(buttonAuthorization).click();
        return new LoginPage(driver);
    }
}
