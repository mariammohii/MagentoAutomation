package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    By sizeOption = By.cssSelector(".swatch-option.text");
    By colorOption = By.cssSelector(".swatch-option.color");
    By addToCartBtn = By.id("product-addtocart-button");

    public void selectSizeAndColor() {
        driver.findElements(sizeOption).get(0).click();
        driver.findElements(colorOption).get(0).click();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartBtn).click();
    }
}
