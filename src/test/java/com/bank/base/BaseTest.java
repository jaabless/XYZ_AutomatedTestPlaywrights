package com.bank.base;
import com.bank.utils.ScreenshotUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.bank.utils.PlaywrightFactory;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.TestInfo;

public class BaseTest {

    protected Page page;

    @BeforeEach
    void setUp() {
        page = PlaywrightFactory.getBrowser()
                .newContext()
                .newPage();

        page.navigate(
                "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login"
        );
    }

    @AfterEach
    void tearDown() {
        if (page != null) {
            page.close(); // ✅ close only the page
        }
    }

//    @AfterEach
//    void tearDown(TestInfo info) {
//        page.context().browser().close();  // ❌ THIS IS THE MAIN PROBLEM
//    }


    @AfterAll
    static void tearDownAll() {
        PlaywrightFactory.closeBrowser(); // ✅ close browser once
    }
}
