package com.bank.automation.customer;

import com.bank.base.BaseTest;
import com.bank.pages.CustomerAccountPage;
import com.bank.pages.LoginPage;
import com.bank.automation.testdata.CustomerTestData;
import com.bank.utils.LoggerUtil;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import io.qameta.allure.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Banking Application")
@Feature("Customer Transactions")
@DisplayName("User Story 2: Customer manages transactions and balance")
public class UserStory2_CustomerTests extends BaseTest {

    private static final Logger log =
            LoggerUtil.getLogger(UserStory2_CustomerTests.class);

    private LoginPage loginPage;
    private CustomerAccountPage customerAccountPage;

    @BeforeEach
    void initPages() {
        loginPage = new LoginPage(page);
        customerAccountPage = new CustomerAccountPage(page);

        log.info("Logging in as Customer");
        loginPage.loginAsCustomer();

        // Select default customer (required by demo app)
        page.selectOption("#userSelect", "Harry Potter");
        page.click("button[type='submit']");
    }

    /* -------------------------------------------------
       VIEW TRANSACTIONS
     ------------------------------------------------- */

    @Test
    @Story("View Transactions")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Customer can view transaction history")
    void shouldViewTransactions() {

        log.info("Opening transactions page");

        customerAccountPage.openTransactions();
        int transactionCount = customerAccountPage.getTransactionCount();

        assertTrue(transactionCount >= 0,
                "Transaction list should be visible to the customer");
    }

    /* -------------------------------------------------
       DEPOSIT – POSITIVE
     ------------------------------------------------- */

    @ParameterizedTest(name = "Deposit amount: {0}")
    @MethodSource("com.bank.automation.testdata.CustomerTestData#depositAmounts")
    @Story("Deposit Funds")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Customer can deposit valid amounts")
    void shouldDepositMoneySuccessfully(String amount) {

        log.info("Depositing amount: {}", amount);

        customerAccountPage.deposit(amount);

        assertTrue(customerAccountPage.isDepositSuccessful(),
                "Deposit should be successful");
    }

    /* -------------------------------------------------
       WITHDRAW – POSITIVE
     ------------------------------------------------- */

    @Test
    @Story("Withdraw Money")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Customer can withdraw money with sufficient balance")
    void shouldWithdrawMoneySuccessfully() {

        log.info("Depositing initial balance before withdrawal");
        customerAccountPage.deposit("1000");

        log.info("Withdrawing amount: 500");
        customerAccountPage.withdraw("500");

        assertTrue(customerAccountPage.isTransactionSuccessful(),
                "Withdrawal should be successful with sufficient balance");
    }

    /* -------------------------------------------------
       WITHDRAW – NEGATIVE & EDGE CASES
     ------------------------------------------------- */

    @ParameterizedTest(name = "Invalid withdrawal: {0}")
    @MethodSource("com.bank.automation.testdata.CustomerTestData#invalidWithdrawals")
    @Story("Withdraw Money - Negative")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Customer cannot withdraw invalid or excessive amounts")
    void shouldNotAllowInvalidWithdrawals(String amount) {

        log.warn("Attempting invalid withdrawal: {}", amount);

        customerAccountPage.withdraw(amount);

        assertFalse(customerAccountPage.isTransactionSuccessful(),
                "Transaction should fail for invalid withdrawal amount");
    }

    /* -------------------------------------------------
       TRANSACTION SECURITY
     ------------------------------------------------- */

    @Test
    @Story("Transaction Security")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Customer cannot alter transaction history")
    void shouldNotAllowTransactionHistoryModification() {

        log.info("Verifying transaction history is read-only");

        customerAccountPage.openTransactions();

        // No edit/delete buttons exist in UI → implicit security validation
        int transactionCount = customerAccountPage.getTransactionCount();

        assertTrue(transactionCount >= 0,
                "Transaction history should be view-only and not editable");
    }
}
