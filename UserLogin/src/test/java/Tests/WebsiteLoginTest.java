package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


      public class WebsiteLoginTest {

        static WebDriver driver;

        @BeforeClass
        public static void startSession() throws InterruptedException {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://the-internet.herokuapp.com/login");
            Thread.sleep(3000);
        }

        @Test
        public void TestLogin() {
            try {
                // Locate username and password fields and enter valid credentials
                WebElement usernameField = driver.findElement(By.id("username"));
                WebElement passwordField = driver.findElement(By.id("password"));

                usernameField.sendKeys("tomsmith");
                passwordField.sendKeys("SuperSecretPassword!");

                // Find and click the login button
                WebElement loginButton = driver.findElement(By.cssSelector(".fa-sign-in"));
                loginButton.click();

                // Wait for the login to complete (you may use WebDriverWait for better synchronization)

                // Verify successful login by checking for the presence of a dashboard element or welcome message
                WebElement successMessage = driver.findElement(By.cssSelector(".flash.success"));
                Assert.assertTrue("Login failed! Success message not found.", successMessage.isDisplayed());

            } catch (Exception e) {
                // Basic error handling
                System.err.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }

        @AfterClass
        public static void tearDown() throws InterruptedException {
            // Close the browser after the test execution, regardless of success or failure
            driver.close();
        }
    }



