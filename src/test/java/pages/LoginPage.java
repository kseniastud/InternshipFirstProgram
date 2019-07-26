package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    private final By loginLocator = By.name("username");
    private final By passwordLocator = By.name("password");
    private final By buttonLogin = By.name("login");

    private LoginPage typeLogin(String login){
        driver.findElement(loginLocator).sendKeys(login);
        return this;
    }

    private LoginPage typePassword(String password){
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public HomePageAfterLogin loginAs(String username, String passwd) {
        typeLogin(username);
        typePassword(passwd);
        driver.findElement(buttonLogin).click();
        return new HomePageAfterLogin(driver);
    }
}
