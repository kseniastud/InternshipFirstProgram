import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.ArrayList;


public class SiteUrlTest {
    private WebDriver driver;
    @BeforeTest
    public void createChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "\\StudentOksanaInternshipFirstProgram\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();      
    }

    @Test
    public void enterToTheSiteAndAssertion(){
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
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        String url2 = driver.getCurrentUrl();
        Assert.assertEquals(url2,url1, "У страниц разные URL");
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }

}
