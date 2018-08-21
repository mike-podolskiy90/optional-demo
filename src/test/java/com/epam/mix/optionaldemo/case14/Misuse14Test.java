package com.epam.mix.optionaldemo.case14;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SuppressWarnings("ALL")
public class Misuse14Test {

    private String misuse(String str) {
        return Optional.of(str)
                .map(greetings())
                .get();
    }

    private Function<String, String> greetings() {
        return str -> str + ", hello!";
    }

    private String useProperly(String str) {
        return greetingsProperly(str);
    }

    private String greetingsProperly(String str) {
        return str + ", hello!";
    }

    @Test
    public void test_case14_misuse() {
        // WHEN
        String actual = misuse("Mike");

        // THEN
        assertThat(actual, is("Mike, hello!"));
    }
}
