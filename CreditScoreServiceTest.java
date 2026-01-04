import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CreditScoreServiceTest {

    CreditScoreService service = new CreditScoreService();

    @Test
    public void testScoreBelowThreshold_ShouldReturnRejected() {
        String result = service.checkLoanEligibility(500);
        assertEquals("Rejected", result);
    }

    @Test
    public void testScoreAtThreshold_ShouldReturnApproved() {
        String result = service.checkLoanEligibility(600);
        assertEquals("Approved", result);
    }

    @Test
    public void testScoreAboveThreshold_ShouldReturnApproved() {
        String result = service.checkLoanEligibility(750);
        assertEquals("Approved", result);
    }

    @Test
    public void testScoreTooLow_ShouldReturnInvalid() {
        String result = service.checkLoanEligibility(200);
        assertEquals("Invalid", result);
    }

    @Test
    public void testScoreTooHigh_ShouldReturnInvalid() {
        String result = service.checkLoanEligibility(900);
        assertEquals("Invalid", result);
    }
}
