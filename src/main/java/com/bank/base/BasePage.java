package com.bank.base;

import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    protected void click(String selector) {
        page.click(selector);
    }

    protected void type(String selector, String value) {
        page.fill(selector, value);
    }

    protected String getText(String selector) {
        return page.textContent(selector);
    }
}

