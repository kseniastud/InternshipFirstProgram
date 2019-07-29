import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SiteUrlTest {
    private WebDriver driver;

    private String searchForCategory(String selectTracker, String inputTrackerContainsTopic) {
        driver.get("http://nnmclub.to");
        String loginUser = "Ксения00788";
        String passwordUser = "zadanie";
        WebElement buttonAuthorization = driver.findElement(By
                .xpath("//*[contains(@class ,'mainmenu') and contains(text(),'Вход')]"));
        buttonAuthorization.click();
        WebElement login = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement buttonLogin = driver.findElement(By.name("login"));
        login.sendKeys(loginUser);
        password.sendKeys(passwordUser);
        buttonLogin.click();
        WebElement searchInputField = driver.findElement(By.name("nm"));
        searchInputField.submit();
        WebElement tracker = driver.findElement(By.id("fs-sel-cat"));
        Select listBoxTracker = new Select(tracker);
        tracker.click();
        listBoxTracker.selectByVisibleText("  ·  " + selectTracker + " ");
        Select listOptgroup = new Select(driver.findElement(By.id("fs")));
        Select listBoxTime = new Select(driver.findElement(By.xpath("//select[@name='tm']")));
        WebElement searchSubmit = driver.findElement(By.name("submit"));
        listOptgroup.selectByVisibleText(" |- " + inputTrackerContainsTopic + " ");
        listBoxTime.selectByValue("-1");
        searchSubmit.submit();
        return inputTrackerContainsTopic;
    }
    private void checkForCategory(String inputTrackerContainsTopic) {
        String countOfPage =driver.findElement(By.xpath("//span/b[2]")).getText();
        int countOfPageParseInt = Integer.parseInt(countOfPage);
        for(int i = 1; i <=countOfPageParseInt; i++){
            List<WebElement> foundCategoryOfSearch = driver.findElements(By.xpath("//td/a[@class='gen']"));
            for (WebElement categoryFound : foundCategoryOfSearch) {
                String categoryFoundText = categoryFound.getText();
                Assert.assertEquals(categoryFoundText, inputTrackerContainsTopic);
            }
            int numberOfPage = i + 1;
            if (countOfPageParseInt!=1 && numberOfPage<=countOfPageParseInt){
                driver.findElement(By.xpath("//span[@class='nav']/a" + "[contains(text()," + numberOfPage + ")]")).click();
            }
        }
    }
    @DataProvider (name = "takeMyCategory")
    public Object[][] myCategory(){
        return new Object[][]{{"Трекер: Всё для детей и родителей", "Отечественные Фильмы для детей"},
                {"Трекер: Всё для детей и родителей", "Обучающее Видео для родителей"},
                {"Трекер: Всё для детей и родителей", "Развивающее Видео для детей"},
                {"Трекер: Программы, Операционные системы", "Сборки Windows 10"},
                {"Трекер: Программы, Операционные системы", "Сборки Windows 7"}
        };
    }
    @Test (dataProvider = "takeMyCategory")
    public void searchAndCheckCategory(String selectTracker, String inputTrackerContainsTopic){
        String categoryTopic = searchForCategory(selectTracker, inputTrackerContainsTopic);
        checkForCategory(categoryTopic);

    }
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
               .name("nm"));
        searchInputField.sendKeys(searchText);
        searchInputField.submit();
        WebElement selectPost = driver.findElement(By.xpath("//select[@name='tm']"));
        selectPost.click();
        WebElement lastThreeMonths = driver.findElement(By
                .xpath("//option[@value='90']"));
        lastThreeMonths.click();
        WebElement searchSubmit =driver.findElement(By.name("submit"));
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

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
   

}
