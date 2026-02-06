package com.bank.pages;

import com.bank.base.BasePage;
import com.bank.utils.LoggerUtil;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import com.microsoft.playwright.options.SelectOption;


public class OpenAccountPage extends BasePage {

    private static final Logger log = LoggerUtil.getLogger(OpenAccountPage.class);

    private final String customerDropdown = "#userSelect";
    private final String currencyDropdown = "#currency";
    private final String processBtn       = "button[type='submit']";

    public OpenAccountPage(Page page) {
        super(page);
    }

    public void openAccount(String customerName, String currency) {
        log.info("Opening account for customer: {} with currency: {}", customerName, currency);

        page.selectOption(
                customerDropdown,
                new SelectOption().setLabel(customerName)
        );

        page.selectOption(
                currencyDropdown,
                new SelectOption().setLabel(currency)
        );

        click(processBtn);

        page.onDialog(dialog -> {
            log.info("Account creation alert: {}", dialog.message());
            dialog.accept();
        });
    }
}
