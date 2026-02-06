package com.bank.pages;

import com.bank.base.BasePage;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    private final String managerLoginBtn = "button[ng-click='manager()']";
    private final String customerLoginBtn = "button[ng-click='customer()']";

    public LoginPage(Page page) {
        super(page);
    }

    public void loginAsManager() {
        click(managerLoginBtn);
    }

    public void loginAsCustomer() {
        click(customerLoginBtn);
    }
}
