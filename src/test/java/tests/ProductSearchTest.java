package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import utils.DriverFactory;

import java.time.Duration;
import java.util.List;

public class ProductSearchTest {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://magento.softwaretestingboard.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
    }

    @Test
    public void searchForHoodie() {
        homePage.searchForProduct("Hoodie");

        // Wait until search results are visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".products-grid .product-item")));

        //To Scroll Down
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500)");

        // Collect product titles
        List<WebElement> productTitles = driver.findElements(By.cssSelector(".product-item .product-item-link"));

        // Assert: At least one result contains "Hoodie"
        boolean atLeastOneMatch = productTitles.stream()
                .anyMatch(element -> element.getText().toLowerCase().contains("hoodie"));

        Assert.assertTrue(atLeastOneMatch, "No search results contain the word 'Hoodie'.");
    }

    @AfterMethod
    public void close() {
        DriverFactory.quitDriver();
    }
}
