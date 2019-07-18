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
    public void createChromeDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver", "\\StudentOksanaInternshipFirstProgram\\driver\\chromedriver.exe");//TODO: change path to make it work everywhere, i don't have such folder
        driver = new ChromeDriver();
        //System.out.println("@BeforeTest");
    }

    @Test
    public void enterToTheSiteAndAssertion() throws Exception{//TODO: seems like your throw statement is never used, trust your IDE :)
        driver.get("https://www.google.ru/");
        driver.manage().window().maximize();//TODO: is it part of test logic?
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);//TODO: is it part of test logic?
        WebElement element = driver.findElement(By.xpath("//input[@name='q']"));//TODO: naming of variable doesn't explains anything
        element.sendKeys("nnm-club");
        element.submit();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);//TODO: is it necessary?
        WebElement element1= driver.findElement(By
                .xpath("//*[contains(text(),'NNM-Club: Торрент-трекер')]"));
        element1.click();//TODO: rename variable
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        String url1 = new String("http://nnmclub.to/");
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);//TODO: is it necessary?
        String url2 = driver.getCurrentUrl();
        Assert.assertEquals(url2,url1, "У страниц разные URL");
        //System.out.println("@Test");//TODO: remove unnecessary strings
    }

    @AfterTest
    public void closeAllTabs() throws Exception{//TODO: change the naming
        driver.quit();
        //System.out.println("@AfterTest");//TODO: !
    }

}
