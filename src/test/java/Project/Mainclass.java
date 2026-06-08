package Project;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Mainclass {

    WebDriver driver;
    JavascriptExecutor js;
    POM_Implementation p;

    @DataProvider(name = "data")
    public Object[][] registerData() {

        return new Object[][] {
                { "Sowmithran", "V",
                        "suryvansi23@gmail.com",
                        "Sowmithran@24",
                        "Sowmithran@24" }
        };
    }

    @DataProvider(name = "Login")
    public Object[][] loginData() {

        return new Object[][] {
                { "Selenium4477@gmail.com", "Sowmithran@24" }
        };
    }

    @DataProvider(name = "search")
    public Object[][] searchData() {

        return new Object[][] {
                { "Laptop", "SmartPhone" }
        };
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://demowebshop.tricentis.com");

        p = new POM_Implementation(driver);
    }

    @Test(priority = 1, dataProvider = "data", groups = "Smoke")
    public void registration(String fname,
                             String lname,
                             String email,
                             String password,
                             String confirmPassword) {

        p.registerUser(
                fname,
                lname,
                email,
                password,
                confirmPassword);
        
        p.logout();
    }

    @Test(priority = 2, dataProvider = "Login", groups = "Smoke")
    public void login(String username,
                      String password) {

        p.login(username, password);
    }

    @Test(priority = 3, dataProvider = "search", groups = "Smoke")
    public void multipleSearch(String product1,
                               String product2) {

        p.searchProducts(product1, product2);
    }

    @Test(priority = 4, groups = "Regression")
    public void addCart() {

        js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,500)");

        p.addToCart();

        js.executeScript("window.scrollBy(500,0)");
    }

    @Test(priority = 5, groups = "Regression")
    public void productDetails() {

        p.getProductDetails();
    }

    @Test(priority = 6, groups = "Regression")
    public void checkout() {

        driver.navigate().back();

        p.checkout("Canada");
    }

    @Test(priority = 7, groups = "Smoke")
    public void deleteCart() {

        driver.navigate().back();

        p.removeFromCart();
    }

    @Test(priority = 8, groups = "Smoke")
    public void logout() {

        p.logout();
    }

    @AfterTest
    public void afterTest() {

		/*
		 * if (driver != null) {
		 * 
		 * driver.quit(); }
		 * 
		 * System.out.println("Testing Completed Successfully");
		 */
    }
}