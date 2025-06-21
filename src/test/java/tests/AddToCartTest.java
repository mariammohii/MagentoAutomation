package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import pages.ProductPage;
import utils.DriverFactory;

import java.time.Duration;

public class AddToCartTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://magento.softwaretestingboard.com/men/tops-men/hoodies-and-sweatshirts-men.html");
    }

    @Test
    public void testAddToCart() {
        driver.findElements(By.cssSelector(".product-item-link")).get(0).click();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectSizeAndColor();
        productPage.clickAddToCart();

        // Wait for the success message to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.message-success.success.message"))
        );

        // Check the message text
        String messageText = successMessage.getText().toLowerCase();
        Assert.assertTrue(messageText.contains("you added") && messageText.contains("to your shopping cart"),
                "Success message is incorrect or missing. Actual message: " + messageText);
    }


    @AfterMethod
    public void close() {
        DriverFactory.quitDriver();
    }
}
