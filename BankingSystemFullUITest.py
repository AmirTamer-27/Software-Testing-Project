from selenium import webdriver
from selenium.webdriver.common.by import By
import unittest

class BankingSystemFullUITest(unittest.TestCase):

    def setUp(self):
        self.driver = webdriver.Chrome()
        self.driver.get("file:///path/to/your/banking_dashboard.html")

    def test_verified_state_functionality(self):
        status = self.driver.find_element(By.ID, "status-label").text
        if status == "Verified":
            self.assertTrue(self.driver.find_element(By.ID, "deposit-btn").is_enabled())
            self.assertTrue(self.driver.find_element(By.ID, "withdraw-btn").is_enabled())
            self.assertTrue(self.driver.find_element(By.ID, "transfer-btn").is_enabled())

    def test_suspended_state_restrictions(self):
        status_label = self.driver.find_element(By.ID, "status-label")
        if "Suspended" in status_label.text:
            transfer_btn = self.driver.find_element(By.ID, "transfer-btn")
            withdraw_btn = self.driver.find_element(By.ID, "withdraw-btn")
            self.assertFalse(transfer_btn.is_enabled())
            self.assertFalse(withdraw_btn.is_enabled())

    def test_closed_state_lockdown(self):
        status_label = self.driver.find_element(By.ID, "status-label")
        if "Closed" in status_label.text:
            deposit_btn = self.driver.find_element(By.ID, "deposit-btn")
            withdraw_btn = self.driver.find_element(By.ID, "withdraw-btn")
            self.assertFalse(deposit_btn.is_enabled())
            self.assertFalse(withdraw_btn.is_enabled())

    def test_input_validation_negative_deposit(self):
        deposit_input = self.driver.find_element(By.ID, "amount-input")
        deposit_btn = self.driver.find_element(By.ID, "deposit-btn")
        notification = self.driver.find_element(By.ID, "notification-box")
        deposit_input.clear()
        deposit_input.send_keys("-50")
        deposit_btn.click()
        self.assertTrue(notification.is_displayed())
        self.assertIn("Invalid", notification.text)

    def tearDown(self):
        self.driver.quit()

if __name__ == "__main__":
    unittest.main()
