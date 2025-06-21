# Magento E-commerce Test Automation

This project is an automated UI test suite for the [Magento Demo Store](https://magento.softwaretestingboard.com/), built using:

- Java
- Selenium WebDriver
- TestNG
- Maven
- Page Object Model (POM)

The goal of this project is to validate core user flows of an e-commerce platform, including product search, adding to cart, and performing a guest checkout.

---

## Project Structure

```
QATask_MariamMohi/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/               # Page Object classes (HomePage, ProductPage, CartPage)
│   └── test/
│       └── java/
│           └── tests/              # TestNG test classes (ProductSearchTest, AddToCartTest, ValidCheckoutTest)
├── testng.xml                      # Test suite definition
├── pom.xml                         # Maven dependencies and plugins
├── report/                         # Copied TestNG HTML report
└── README.md                       # Project overview and setup instructions

```

---

## Setup Instructions

### Prerequisites

- Java JDK 8 or above
- Maven installed and configured in `PATH`
- Internet connection (for WebDriverManager to download drivers)
- IntelliJ IDEA (recommended)

---

### How to Install Dependencies

Maven will handle everything. Just run:

bash --> mvn clean install 

#### This will download:
- Selenium WebDriver
- TestNG
- WebDriverManager

---

### Running the Tests

Run all tests using Maven + TestNG:

- bash --> mvn clean test
- Or, if using IntelliJ: Right-click testng.xml > Run 'testng.xml'

---

### Test Covarage

| Test Class          | Description                               |
| ------------------- | ----------------------------------------- |
| `ProductSearchTest` | Searches for a product (e.g., "Hoodie")   |
| `AddToCartTest`     | Opens a product page and adds to the cart |
| `ValidCheckoutTest` | Completes a guest checkout successfully   |

These cover the main user journey: search → add to cart → checkout 

---

### Test Reports

After running the tests, an HTML report is generated and copied to:
--> report/index.html

---

### Report Sample

report/
├── index.html
├── emailable-report.html
├── testng-results.xml
