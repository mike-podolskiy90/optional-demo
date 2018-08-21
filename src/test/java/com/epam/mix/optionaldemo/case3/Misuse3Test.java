package com.epam.mix.optionaldemo.case3;

import com.epam.mix.optionaldemo.case3.objects.Apartment;
import com.epam.mix.optionaldemo.case3.objects.FullName;
import com.epam.mix.optionaldemo.case3.objects.Person;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;

public class Misuse3Test {

    private Optional<String> misuse() {
        Apartment apartment = new Apartment();

        return Optional.of(apartment)
                .map(flat -> flat.getOwner().getFullName().getFirstName());
    }

    private Optional<String> useProperly() {
        Apartment apartment = new Apartment();

        return Optional.of(apartment)
                .map(Apartment::getOwner)
                .map(Person::getFullName)
                .map(FullName::getFirstName);
    }

    @Test
    public void test_case3_misuse() {
        // WHEN
        Optional<String> misuse = useProperly();

        // THEN
        assertFalse(misuse.isPresent());
    }
}
