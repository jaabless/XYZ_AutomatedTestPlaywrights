package com.bank.pages;

import com.bank.base.BasePage;
import com.bank.utils.LoggerUtil;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;

public class CustomerAccountPage extends BasePage {

    private static final Logger log = LoggerUtil.getLogger(CustomerAccountPage.class);

    private final String depositTab     = "button[ng-click='deposit()']";
    private final String withdrawTab    = "button[ng-click='withdrawl()']";
    private final String transactionsTab= "button[ng-click='transactions()']";

    private final String amountInput    = "input[ng-model='amount']";
    private final String submitBtn      = "button[type='submit']";
    private final String messageLabel   = "span[ng-show='message']";
    private final String balanceLabel   = "strong.ng-binding:nth-of-type(2)";

    public CustomerAccountPage(Page page) {
        super(page);
    }

    /* -------------------- Deposits -------------------- */

    public void deposit(String amount) {
        log.info("Depositing amount: {}", amount);
        click(depositTab);
        type(amountInput, amount);
        click(submitBtn);
    }

    public boolean isDepositSuccessful() {
        return getText(messageLabel).contains("Deposit Successful");
    }

    /* -------------------- Withdrawals -------------------- */

    public void withdraw(String amount) {
        log.info("Withdrawing amount: {}", amount);
        click(withdrawTab);
        type(amountInput, amount);
        click(submitBtn);
    }

    public boolean isTransactionSuccessful() {
        return getText(messageLabel).contains("Transaction successful");
    }

    /* -------------------- Transactions -------------------- */

    public void openTransactions() {
        log.info("Opening transactions tab");
        click(transactionsTab);
    }

    public int getTransactionCount() {
        return page.locator("table tbody tr").count();
    }

    public String getBalance() {
        return getText(balanceLabel);
    }
}

