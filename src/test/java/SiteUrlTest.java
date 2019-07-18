import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SiteUrlTest {
    private WebDriver driver;
    @BeforeTest
    public void createChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "\\automation\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        System.out.println("@BeforeTest");
    }

    @Test
    public void enterToTheSiteAndAssertion(){

    }

    @AfterTest
    public void closeAllTabs(){

    }
}
