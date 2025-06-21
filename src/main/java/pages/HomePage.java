package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        }
        private By searchBox = By.id("search");
        private By searchButton = By.cssSelector("button[title='Search']");

        public void searchForProduct(String product) {
            driver.findElement(searchBox).sendKeys(product);
            driver.findElement(searchButton).click();
        }
}

