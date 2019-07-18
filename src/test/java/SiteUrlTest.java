import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SiteUrlTest {
    private WebDriver driver;
    @BeforeTest
    public void createChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "\\StudentOksanaInternshipFirstProgram\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("@BeforeTest");
    }

    @Test
    public void enterToTheSiteAndAssertion(){
        driver.get("https://www.google.ru/");
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//input[@name='q']"));
        element.sendKeys("nnm-club");
        element.submit();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        WebElement element1= driver.findElement(By
                .xpath("//*[contains(text(),'NNM-Club: Торрент-трекер')]"));
        element1.click();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        String url1 = new String("http://nnmclub.to/");
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
        String url2 = driver.getCurrentUrl();
        Assert.assertEquals(url2,url1, "У страниц разные URL");
        System.out.println("@Test");
    }

    @AfterTest
    public void closeAllTabs(){
        driver.quit();
        System.out.println("@AfterTest");
    }

}
