package com.bank.pages;

import com.bank.base.BasePage;
import com.bank.utils.LoggerUtil;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;

public class CustomersPage extends BasePage {

    private static final Logger log = LoggerUtil.getLogger(CustomersPage.class);

    private final String searchInput = "input[ng-model='searchCustomer']";

    public CustomersPage(Page page) {
        super(page);
    }

    private String deleteButtonByFirstName(String firstName) {
        return "//td[text()='" + firstName + "']/following-sibling::td/button";
    }

    public void searchCustomer(String name) {
        log.info("Searching for customer: {}", name);
        type(searchInput, name);
    }

    public void deleteCustomer(String firstName) {
        log.info("Deleting customer with first name: {}", firstName);
        page.click(deleteButtonByFirstName(firstName));
    }

    public boolean isCustomerPresent(String firstName) {
        return page.locator("//td[text()='" + firstName + "']").count() > 0;
    }
}
