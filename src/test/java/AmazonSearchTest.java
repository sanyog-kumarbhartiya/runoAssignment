package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmazonSearchTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        // Set up ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Maximize the window
        driver.manage().window().maximize();
    }

    @Test
    public void testAmazonSearch() {
        try {
            // Open Amazon.in
            driver.get("https://www.amazon.in");

            // Locate the search box
            WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));

            // Click and enter "laptop"
            searchBox.click();
            searchBox.sendKeys("laptop");

            // Submit the search
            searchBox.submit();

            // Wait for results to load
            Thread.sleep(3000);

            // Verify search results are displayed
            WebElement results = driver.findElement(By.cssSelector("span.a-color-state"));
            assertTrue(results.isDisplayed(), "Search results are not displayed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
