package com.bank.utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Attachment;

public class ScreenshotUtil {

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] takeScreenshot(Page page, String name) {
        return page.screenshot();
    }
}

