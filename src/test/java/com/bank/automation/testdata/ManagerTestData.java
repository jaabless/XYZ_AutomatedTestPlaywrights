package com.bank.automation.testdata;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ManagerTestData {

    public static Stream<Arguments> validCustomers() {
        return Stream.of(
                Arguments.of("John", "Doe", "12345"),
                Arguments.of("Alice", "Smith", "54321")
        );
    }

    public static Stream<Arguments> invalidCustomers() {
        return Stream.of(
                Arguments.of("John1", "Doe", "12345"),
                Arguments.of("Alice", "Smith", "ABC12")
        );
    }
}
