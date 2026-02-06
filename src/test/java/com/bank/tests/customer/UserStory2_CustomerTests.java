package com.bank.tests.customer;
import com.bank.base.BaseTest;
import com.bank.pages.*;
import com.bank.tests.testdata.ManagerTestData;
import com.bank.utils.LoggerUtil;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;

@Epic("Banking Application")
@Feature("Customer Transactions")
public class UserStory2_CustomerTests extends BaseTest {

    @ParameterizedTest
    @MethodSource("com.bank.automation.testdata.CustomerTestData#depositAmounts")
    @Story("Deposit Funds")
    @Severity(SeverityLevel.CRITICAL)
    void depositMoney(String amount) {
        LoginPage login = new LoginPage(page);
        login.loginAsCustomer();

        CustomerAccountPage account = new CustomerAccountPage(page);
        account.deposit(amount);

        Assertions.assertTrue(account.isDepositSuccessful());
    }

    @ParameterizedTest
    @MethodSource("com.bank.automation.testdata.CustomerTestData#invalidWithdrawals")
    @Story("Withdraw Money - Negative")
    @Severity(SeverityLevel.BLOCKER)
    void withdrawInvalidAmount(String amount) {
        LoginPage login = new LoginPage(page);
        login.loginAsCustomer();

        CustomerAccountPage account = new CustomerAccountPage(page);
        account.withdraw(amount);

        Assertions.assertFalse(account.isTransactionSuccessful());
    }
}
