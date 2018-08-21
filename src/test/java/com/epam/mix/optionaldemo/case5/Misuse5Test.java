package com.epam.mix.optionaldemo.case5;

import com.epam.mix.optionaldemo.case5.objects.Person;
import com.epam.mix.optionaldemo.case5.objects.PersonService;
import org.junit.Test;

public class Misuse5Test {

    private PersonService personService;

    private Person misuse() {
        return personService.get().orElseThrow(IllegalStateException::new);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_case5_misuse() {
        // WHEN
        Person actual = misuse();
    }
}
