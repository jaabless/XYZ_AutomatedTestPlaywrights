package com.bank.utils;

import com.microsoft.playwright.*;
import org.slf4j.Logger;

public class PlaywrightFactory {

    private static final Logger log = LoggerUtil.getLogger(PlaywrightFactory.class);

    private static Playwright playwright;
    private static Browser browser;

    private PlaywrightFactory() {}

    /* -------------------- Browser Initialization -------------------- */

    public static Browser getBrowser() {
        if (browser == null) {
            log.info("Initializing Playwright Browser");
            playwright = Playwright.create();

            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                    .setHeadless(false)          // set true for CI
                    .setSlowMo(50);               // helps debug flaky UI

            browser = playwright.chromium().launch(options);
        }
        return browser;
    }

    /* -------------------- Cleanup -------------------- */

    public static void closeBrowser() {
        log.info("Closing Playwright Browser");
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
