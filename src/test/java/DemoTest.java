import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DemoTest {
    public static WebDriver driver;
    public static String url = "https://am.globbing.com/hy";
    public static String krispurl = "https://krisp.ai/";

    @BeforeSuite
    public static void initDriver(){
        System.setProperty("webdriver.chrome.driver", "driverinit/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void openSUT(){
        driver.get(krispurl);
    }

    @Test
    public void test(){
        driver.findElement(By.className("sign-in")).click();
        driver.findElement(By.className("fw-600")).click();
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div/div/div/form/div[1]/label")).click();
        driver.findElement(By.id("user_email")).sendKeys("test@test");
        driver.findElement(By.className("input-feedback")).getText();
    }

    @AfterSuite
    public static void tearDown(){
        driver.quit();
    }


}
