import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;


public class SiteUrlTest {
    private WebDriver driver;
    @BeforeMethod
    public void createChromeDriver(){
        System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();      
    }

    @Test
    public void enterToTheSiteAndAssertion() {
        driver.get("https://www.google.ru/");
        WebElement input = driver.findElement(By.xpath("//input[@name='q']"));
        input.sendKeys("nnm-club");
        input.submit();
        WebElement searchTheText= driver.findElement(By
                .xpath("//*[contains(text(),'NNM-Club: Торрент-трекер')]"));
        searchTheText.click();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        String url1 = "http://nnmclub.to/";
        String url2 = driver.getCurrentUrl();
        Assert.assertEquals(url2,url1, "У страниц разные URL");
    }
     @Test
    public void loginToTheSiteAndVerification(){
        driver.get("http://nnmclub.to");
        WebElement buttonAuthorization = driver.findElement(By
                .xpath("//*[contains(@class ,'mainmenu') and contains(text(),'Вход')]"));
        buttonAuthorization.click();
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("Ксения00788");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("zadanie");
        WebElement buttonLogin =driver.findElement(By.name("login"));
        buttonLogin.click();
        Assert.assertTrue(driver.findElement(By
                .xpath("//*[contains(@class ,'mainmenu') and contains(text(),'Выход')]")).isDisplayed(), "Вход на сайт не был успешным");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
   

}
