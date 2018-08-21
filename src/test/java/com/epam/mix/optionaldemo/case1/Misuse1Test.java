package com.epam.mix.optionaldemo.case1;

import org.junit.Test;

import java.util.Optional;

@SuppressWarnings("ALL")
public class Misuse1Test {

    private StringService stringService = new StringService();

    private void misuse() {
        Optional<String> strOpt = stringService.getString();

        if (strOpt.isPresent()) {
            System.out.println(strOpt.get());
        }
    }

    private void useProperly() {
        Optional<String> str = stringService.getString();
        str.ifPresent(System.out::println);
    }

    @Test
    public void test_case1_misuse() {
        // WHEN
        misuse();
    }
}
