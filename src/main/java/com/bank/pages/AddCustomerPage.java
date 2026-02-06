package com.bank.pages;

import com.bank.base.BasePage;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;

public class AddCustomerPage extends BasePage {

    private final String firstName = "input[ng-model='fName']";
    private final String lastName = "input[ng-model='lName']";
    private final String postCode = "input[ng-model='postCd']";
    private final String addCustomerBtn = "button[type='submit']";

    public AddCustomerPage(Page page) {
        super(page);
    }

    public void addCustomer(String fName, String lName, String postal) {
        type(firstName, fName);
        type(lastName, lName);
        type(postCode, postal);
        click(addCustomerBtn);
        page.onDialog(Dialog::accept);
    }
}

