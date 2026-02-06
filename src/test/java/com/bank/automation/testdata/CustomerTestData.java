package com.bank.automation.testdata;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;


public class CustomerTestData {

    public static Stream<Arguments> depositAmounts() {
        return Stream.of(
                Arguments.of("100"),
                Arguments.of("500")
        );
    }

    public static Stream<Arguments> invalidWithdrawals() {
        return Stream.of(
                Arguments.of("-50"),
                Arguments.of("100000")
        );
    }
}

