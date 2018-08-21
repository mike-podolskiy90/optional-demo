package com.epam.mix.optionaldemo.case2;

import org.junit.Test;

import java.util.Optional;

@SuppressWarnings("ALL")
public class Misuse2Test {
    private StringService stringService = new StringService();

    private void misuse() {
        Optional<String> strOpt = stringService.getEmptyString();

        System.out.println(strOpt.get());
    }

    private void useProperly() {
        Optional<String> str = stringService.getEmptyString();
        str.ifPresent(System.out::println);
    }

    @Test
    public void test_case1_misuse() {
        // WHEN
        misuse();
    }
}
