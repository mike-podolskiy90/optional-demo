package com.epam.mix.optionaldemo.case13;

import com.epam.mix.optionaldemo.case13.objects.Country;
import com.epam.mix.optionaldemo.case13.objects.CountryEntity;
import com.epam.mix.optionaldemo.case13.objects.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SuppressWarnings("ALL")
@RunWith(MockitoJUnitRunner.class)
public class Misuse13Test {

    @Mock
    private CountryService countryService;

    private CountryEntity globalCountryEntity;

    @Before
    public void setUp() {
        globalCountryEntity = new CountryEntity();
        globalCountryEntity.setId(1L);
        globalCountryEntity.setName("country 1");

        when(countryService.get()).thenReturn(Optional.of(globalCountryEntity));
    }

    private Optional<Country> misuse(CountryEntity value) {
        if (Objects.isNull(value)) {
            return Optional.empty();
        }

        return Optional.of(
                Country.builder()
                        .id(value.getId())
                        .name(value.getName())
                        .build()
        );
    }

    private Optional<Country> useProperly(CountryEntity value) {
        return Optional.ofNullable(value)
                .map(countryEntity -> new Country(countryEntity.getId(), countryEntity.getName()));
    }

    private Optional<Country> useAbsolutelyProperly() {
        Optional<CountryEntity> countryEntityOpt = countryService.get();

        return countryEntityOpt.map(this::map);
    }

    private Country map(CountryEntity countryEntity) {
        return new Country(countryEntity.getId(), countryEntity.getName());
    }

    @Test
    public void test_case13_misuse() {
        // WHEN
        Optional<Country> actual = useAbsolutelyProperly();

        // THEN
        assertTrue(actual.isPresent());
        assertThat(actual.get().getId(), is(1L));
        assertThat(actual.get().getName(), is("country 1"));

    }
}
