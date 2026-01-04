import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AccountBlackBoxTest {

    @Test
    public void depositNegativeAmount_shouldFail() {
        Account acc = new Account(100, "Verified");
        assertFalse(acc.deposit(-100));
    }

    @Test
    public void depositZero_shouldFail() {
        Account acc = new Account(100, "Verified");
        assertFalse(acc.deposit(0));
    }

    @Test
    public void depositValidAmount_shouldSucceed() {
        Account acc = new Account(100, "Verified");
        assertTrue(acc.deposit(50));
        assertEquals(150, acc.getBalance());
    }

    @Test
    public void withdrawWithinBalance_shouldSucceed() {
        Account acc = new Account(200, "Verified");
        assertTrue(acc.withdraw(50));
        assertEquals(150, acc.getBalance());
    }

    @Test
    public void withdrawExceedBalance_shouldFail() {
        Account acc = new Account(100, "Verified");
        assertFalse(acc.withdraw(200));
    }
}
