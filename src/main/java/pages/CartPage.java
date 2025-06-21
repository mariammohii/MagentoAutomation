package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    By miniCart = By.cssSelector("a.action.showcart");
    By proceedToCheckout = By.id("top-cart-btn-checkout");

    public void openMiniCart() {
        driver.findElement(miniCart).click();
    }

    public void proceedToCheckout() {
        driver.findElement(proceedToCheckout).click();
    }
}

