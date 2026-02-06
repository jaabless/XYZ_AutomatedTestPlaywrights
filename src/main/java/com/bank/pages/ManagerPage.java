package com.bank.pages;

import com.bank.base.BasePage;
import com.bank.utils.LoggerUtil;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;

public class ManagerPage extends BasePage {

    private static final Logger log = LoggerUtil.getLogger(ManagerPage.class);

    private final String addCustomerBtn = "button[ng-click='addCust()']";
    private final String openAccountBtn = "button[ng-click='openAccount()']";
    private final String customersBtn   = "button[ng-click='showCust()']";

    public ManagerPage(Page page) {
        super(page);
    }

    public void goToAddCustomer() {
        log.info("Navigating to Add Customer page");
        click(addCustomerBtn);
    }

    public void goToOpenAccount() {
        log.info("Navigating to Open Account page");
        click(openAccountBtn);
    }

    public void goToCustomers() {
        log.info("Navigating to Customers page");
        click(customersBtn);
    }
}
