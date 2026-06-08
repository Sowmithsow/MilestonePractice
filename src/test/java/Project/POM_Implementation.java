package Project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class POM_Implementation {

    WebDriver driver;
    WebDriverWait wait;

    public POM_Implementation(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Registration
    private By registerLink = By.linkText("Register");
    private By genderMale = By.id("gender-male");
    private By firstName = By.id("FirstName");
    private By lastName = By.id("LastName");
    private By emailField = By.id("Email");
    private By passwordField = By.id("Password");
    private By confirmPassword = By.id("ConfirmPassword");
    private By registerButton = By.id("register-button");
    private By continueButton = By.cssSelector(".register-continue-button");

    // Login
    private By loginLink = By.xpath("//a[@href='/login']");
    private By loginEmail = By.id("Email");
    private By loginPassword = By.id("Password");
    private By loginButton = By.xpath("//input[@value='Log in']");

    // Search Product
    private By searchBox = By.id("small-searchterms");
    private By searchButton = By.cssSelector(".search-box-button");

    // Product Details
    private By productLink = By.linkText("Smartphone");
    private By productName = By.xpath("//h1[@itemprop='name']");
    private By productPrice = By.xpath("//span[@itemprop='price']");

    // Cart
    private By addToCartButton = By.xpath("//input[@value='Add to cart']");
    private By shoppingCart = By.xpath("//span[@class='cart-label']");

    // Checkout
    private By countryDropdown = By.id("CountryId");
    private By termsCheckbox = By.id("termsofservice");
    private By checkoutButton = By.id("checkout");

    // Remove Cart
    private By removeCheckbox = By.xpath("//input[@name='removefromcart']");
    private By updateCartButton = By.xpath("//input[@name='updatecart']");

    // Logout
    private By logoutLink = By.linkText("Log out");

    // Registration
    public void registerUser(String fName, String lName,
                             String email, String password, String confirmPass) {

        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();

        driver.findElement(genderMale).click();
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPassword).sendKeys(confirmPass);

        driver.findElement(registerButton).click();

        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    // Login
    public void login(String email, String password) {

        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();

        driver.findElement(loginEmail).sendKeys(email);
        driver.findElement(loginPassword).sendKeys(password);

        driver.findElement(loginButton).click();
    }

    // Search Multiple Products
    public void searchProducts(String... products) {

        for (String item : products) {

            WebElement search = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(searchBox));

            search.clear();
            search.sendKeys(item);

            driver.findElement(searchButton).click();
        }
    }

    // Add Product To Cart
    public void addToCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(addToCartButton))
                .click();

        wait.until(
                ExpectedConditions.elementToBeClickable(shoppingCart))
                .click();
    }

    // Product Details
    public void getProductDetails() {

        wait.until(
                ExpectedConditions.elementToBeClickable(productLink))
                .click();

        String name = driver.findElement(productName).getText();
        String price = driver.findElement(productPrice).getText();

        System.out.println("Product Name : " + name);
        System.out.println("Product Price : " + price);
    }

    // Checkout
    public void checkout(String countryName) {

        Select select = new Select(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(countryDropdown)));

        select.selectByVisibleText(countryName);

        driver.findElement(termsCheckbox).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton))
                .click();
    }

    // Remove Product From Cart
    public void removeFromCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(removeCheckbox))
                .click();

        driver.findElement(updateCartButton).click();
    }

    // Logout
    public void logout() {

        wait.until(
                ExpectedConditions.elementToBeClickable(logoutLink))
                .click();
    }
}