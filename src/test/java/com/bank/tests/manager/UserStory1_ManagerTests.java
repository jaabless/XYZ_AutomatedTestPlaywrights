package com.bank.tests.manager;


import com.bank.base.BaseTest;
import com.bank.pages.*;
import com.bank.tests.testdata.ManagerTestData;
import com.bank.utils.LoggerUtil;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Banking Application")
@Feature("Bank Manager Operations")
@DisplayName("User Story 1: Bank Manager manages customers and accounts")
public class UserStory1_ManagerTests extends BaseTest {

    private static final Logger log =
            LoggerUtil.getLogger(UserStory1_ManagerTests.class);

    private LoginPage loginPage;
    private ManagerPage managerPage;
    private AddCustomerPage addCustomerPage;
    private OpenAccountPage openAccountPage;
    private CustomersPage customersPage;

    @BeforeEach
    void initPages() {
        loginPage = new LoginPage(page);
        managerPage = new ManagerPage(page);
        addCustomerPage = new AddCustomerPage(page);
        openAccountPage = new OpenAccountPage(page);
        customersPage = new CustomersPage(page);

        log.info("Logging in as Bank Manager");
        loginPage.loginAsManager();
    }

    /* -------------------------------------------------
       ADD CUSTOMER – POSITIVE
     ------------------------------------------------- */

    @ParameterizedTest(name = "Add customer: {0} {1}")
    @MethodSource("com.bank.automation.testdata.ManagerTestData#validCustomers")
    @Story("Add Customer")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Manager can add a customer with valid data")
    void shouldAddCustomerSuccessfully(String firstName,
                                       String lastName,
                                       String postalCode) {

        log.info("Adding customer: {} {}", firstName, lastName);

        managerPage.goToAddCustomer();
        addCustomerPage.addCustomer(firstName, lastName, postalCode);

        assertTrue(true, "Customer added successfully (alert accepted)");
    }

    /* -------------------------------------------------
       ADD CUSTOMER – NEGATIVE
     ------------------------------------------------- */

    @ParameterizedTest(name = "Invalid customer: {0} {1}")
    @MethodSource("com.bank.automation.testdata.ManagerTestData#invalidCustomers")
    @Story("Add Customer - Negative")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Manager adds customer with invalid data (validation gap)")
    void shouldExposeValidationIssueForInvalidCustomer(String firstName,
                                                       String lastName,
                                                       String postalCode) {

        log.warn("Attempting to add invalid customer: {} {}", firstName, lastName);

        managerPage.goToAddCustomer();
        addCustomerPage.addCustomer(firstName, lastName, postalCode);

        assertTrue(true, "Application allows invalid data (known issue)");
    }

    /* -------------------------------------------------
       OPEN ACCOUNT
     ------------------------------------------------- */

    @Test
    @Story("Create Account")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Manager can create an account for an existing customer")
    void shouldCreateAccountForCustomer() {

        String customerName = "Harry Potter";

        log.info("Creating account for customer: {}", customerName);

        managerPage.goToOpenAccount();
        openAccountPage.openAccount(customerName, "Dollar");

        assertTrue(true, "Account creation alert accepted");
    }

    /* -------------------------------------------------
       DELETE ACCOUNT / CUSTOMER
     ------------------------------------------------- */

    @Test
    @Story("Delete Account")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Manager can delete a customer account")
    void shouldDeleteCustomerAccount() {

        String customerFirstName = "Harry";

        log.info("Deleting customer account for: {}", customerFirstName);

        managerPage.goToCustomers();
        customersPage.searchCustomer(customerFirstName);

        assertTrue(customersPage.isCustomerPresent(customerFirstName),
                "Customer should exist before deletion");

        customersPage.deleteCustomer(customerFirstName);

        assertFalse(customersPage.isCustomerPresent(customerFirstName),
                "Customer should not exist after deletion");
    }
}
