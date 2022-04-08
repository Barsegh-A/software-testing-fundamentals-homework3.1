import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class SignUpTest {
    public static WebDriver driver;
    public static String url = "https://krisp.ai/";

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
    public void wrongEmailFormatErrorMessageTest(){
        driver.findElement(By.className("sign-in")).click();
        driver.findElement(By.linkText("Sign up")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"wrapper\"]/div/div/div/form/div[1]/label")))
                .click();
        driver.findElement(By.id("user_email")).sendKeys("test@test");
        driver.findElement(By.cssSelector("#wrapper > div > div > div > form > button.btn.btn-primary.btn-lg.btn-block")).click();
        String actualErrorMessage = driver.findElement(By.className("input-feedback")).getText();
        assertEquals(actualErrorMessage, "Wrong email format", "Error message is wrong");
    }

    @AfterSuite
    public static void tearDown(){
        driver.quit();
    }
}
