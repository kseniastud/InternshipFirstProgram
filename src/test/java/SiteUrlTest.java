import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SiteUrlTest {
    private WebDriver driver;
    @BeforeTest
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
    public void searchFunctionalityTest() throws ParseException {
        String searchText = "java";
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
        WebElement searchInputField = driver.findElement(By
               .xpath("//input[@name='nm']"));
        searchInputField.sendKeys(searchText);
        WebElement buttonSearch = driver.findElement(By.name("search_submit"));
        buttonSearch.click();
        WebElement selectPost = driver.findElement(By.xpath("//select[@name='tm']"));
        selectPost.click();
        WebElement lastThreeMonths = driver.findElement(By
                .xpath("//option[@value='90']"));
        lastThreeMonths.click();
        WebElement searchSubmit =driver.findElement(By.className("liteoption"));
        searchSubmit.click();
        List<WebElement> foundTopicsOfSearch = driver.findElements(By.xpath("//*[@class='genmed topictitle']"));
        for(WebElement topic:foundTopicsOfSearch){
            String topicLowerCase = topic.getText().toLowerCase();
            Assert.assertTrue(topicLowerCase.contains(searchText), "Тема не содержит значения "+searchText);
        }
       Calendar calendar = new GregorianCalendar();
       Date nowDate = calendar.getTime();
       calendar.add(Calendar.MONTH,-3);
       Date last3MonthDate = calendar.getTime();
        List<WebElement> foundDataOfSearch = driver.findElements(By.xpath("//td[@title='Добавлено' and @nowrap ='nowrap']"));
        for (WebElement data:foundDataOfSearch){
            String dataString = data.getText().substring(0,10);
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date dataFromStringToDate = format.parse(dataString);
            Assert.assertTrue(dataFromStringToDate.before(nowDate) && dataFromStringToDate.after(last3MonthDate), "Дата не соответствует условию: за последние три месяца") ;
        }

    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();
    }
   

}
