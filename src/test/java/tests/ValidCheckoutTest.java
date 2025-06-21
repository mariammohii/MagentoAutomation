package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CartPage;
import utils.DriverFactory;
import java.time.Duration;

public class ValidCheckoutTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.getDriver();
        driver.get("https://magento.softwaretestingboard.com/men/tops-men/hoodies-and-sweatshirts-men.html");
    }

    @Test
    public void testGuestCheckout() throws InterruptedException {

        // Reuse add to cart test without duplicating code
        AddToCartTest addTest = new AddToCartTest();
        addTest.driver = this.driver;  // share the same driver
        addTest.testAddToCart();       // call the original method

        // proceed to checkout
        CartPage cart = new CartPage(driver);
        cart.openMiniCart();
        cart.proceedToCheckout();

        // Wait for checkout page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer-email")));

        // Fill customer email
        driver.findElement(By.id("customer-email")).sendKeys("testuser02@example.com");

        // Fill shipping address
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstname"))).sendKeys("Test");
        driver.findElement(By.name("lastname")).sendKeys("User");
        driver.findElement(By.name("street[0]")).sendKeys("123 Test St");
        driver.findElement(By.name("city")).sendKeys("Cairo");
        driver.findElement(By.name("postcode")).sendKeys("12345");

        // Wait for Country dropdown to appear
        WebElement countryElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("country_id"))
        );
        Select countryDropdown = new Select(countryElement);
        countryDropdown.selectByVisibleText("Egypt");

        driver.findElement(By.name("telephone")).sendKeys("01012345678");

        Thread.sleep(3000); //Not the best approach  but it only works with it

        // Wait until shipping methods fully reload (after country/region selection)
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading-mask")));

        // Re-locate the radio button after update
        WebElement shippingMethod = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='radio'][name][value]"))
        ); shippingMethod.click();

        // Wait again for "Next" button to stabilize
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button")));
        wait.until(driver -> nextButton.isDisplayed() && nextButton.isEnabled());
        nextButton.click();

        Thread.sleep(3000);
        WebElement placeOrderButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.checkout"))
        ); placeOrderButton.click();

        Thread.sleep(3000);
        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[2]/p[1]"))
        );

        Thread.sleep(3000);
        String successText = successMessage.getText().toLowerCase();
        Assert.assertTrue(successText.contains("your order # is"),
                "Checkout failed or order number not found. Message: " + successText);
    }

    @AfterMethod
    public void close() {
        DriverFactory.quitDriver();
    }
}
