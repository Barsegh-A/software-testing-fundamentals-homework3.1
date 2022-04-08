import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class SpyurTest {
    public static WebDriver driver;
    public static String url = "https://spyur.am/";

    @BeforeSuite
    public static void initDriver(){
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openSUT(){
        driver.get(url);
    }

    @Test
    public void auaPageTest(){
        driver.findElement(By.name("company_name")).sendKeys("Ամերիկյան համալսարան");
        driver.findElement(By.className("checkbox_button")).click();
        driver.findElement(By.xpath("//*[@id=\"search\"]/button")).click();
        driver.findElement(By.cssSelector("#results_list_wrapper > a:nth-child(1)")).click();

        String title = driver.findElement(By.className("page_title")).getText();
        assertEquals(title, "ՀԱՅԱՍՏԱՆԻ ԱՄԵՐԻԿՅԱՆ ՀԱՄԱԼՍԱՐԱՆ", "Wrong title of result page for AUA");

        List<WebElement> phoneNumbers = driver.findElements(By.className("call"));
        assertEquals(phoneNumbers.size(), 3, "Wrong number of phone numbers for AUA");
    }

    @AfterSuite
    public static void tearDown(){
        driver.quit();
    }
}
