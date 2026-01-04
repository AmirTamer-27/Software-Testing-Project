public class CreditScoreService {
    public String checkLoanEligibility(int creditScore) {
        if (creditScore < 300 || creditScore > 850) {
            return "Invalid";
        }
        if (creditScore >= 600) {
            return "Approved";
        } else {
            return "Rejected";
        }
    }
}
