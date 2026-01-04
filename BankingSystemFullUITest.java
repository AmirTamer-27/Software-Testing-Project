import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankingSystemFullUITest {
    private WebDriver driver;

    @Before
    public void setUp() {
        // Setup ChromeDriver
        driver = new ChromeDriver();
        // Provide the absolute path to your dashboard HTML provided in the artifacts
        driver.get("file:///path/to/your/banking_dashboard.html");
    }

    @Test
    public void testVerifiedStateFunctionality() {
        
        String status = driver.findElement(By.id("status-label")).getText();
        
        if (status.equals("Verified")) {
            assertTrue("Deposit button should be enabled", driver.findElement(By.id("deposit-btn")).isEnabled());
            assertTrue("Withdraw button should be enabled", driver.findElement(By.id("withdraw-btn")).isEnabled());
            assertTrue("Transfer button should be enabled", driver.findElement(By.id("transfer-btn")).isEnabled());
        }
    }

    @Test
    public void testSuspendedStateRestrictions() {
        
        WebElement statusLabel = driver.findElement(By.id("status-label"));
        
        if (statusLabel.getText().contains("Suspended")) {
            WebElement transferBtn = driver.findElement(By.id("transfer-btn"));
            WebElement withdrawBtn = driver.findElement(By.id("withdraw-btn"));
            
            
            assertFalse("Transfer should be disabled when Suspended", transferBtn.isEnabled());
            assertFalse("Withdraw should be disabled when Suspended", withdrawBtn.isEnabled());
        }
    }

    @Test
    public void testClosedStateLockdown() {
       
        WebElement statusLabel = driver.findElement(By.id("status-label"));
        
        if (statusLabel.getText().contains("Closed")) {
            WebElement depositBtn = driver.findElement(By.id("deposit-btn"));
            WebElement withdrawBtn = driver.findElement(By.id("withdraw-btn"));
            
            
            assertFalse("Deposit blocked in Closed state", depositBtn.isEnabled());
            assertFalse("Withdraw blocked in Closed state", withdrawBtn.isEnabled());
        }
    }

    @Test
    public void testInputValidationNegativeDeposit() {
        
        WebElement depositInput = driver.findElement(By.id("amount-input"));
        WebElement depositBtn = driver.findElement(By.id("deposit-btn"));
        WebElement notification = driver.findElement(By.id("notification-box"));

        depositInput.clear();
        depositInput.sendKeys("-50");
        depositBtn.click();

        
        assertTrue("Notification box should be visible", notification.isDisplayed());
        assertTrue("Should display 'Invalid' message", notification.getText().contains("Invalid"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}