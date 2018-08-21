package com.epam.mix.optionaldemo.case10;

import com.epam.mix.optionaldemo.case10.objects.Division;
import com.epam.mix.optionaldemo.case10.objects.DivisionEntity;
import com.epam.mix.optionaldemo.case10.objects.DivisionRepository;
import com.epam.mix.optionaldemo.case10.objects.ProcessEntity;
import com.epam.mix.optionaldemo.case10.objects.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@SuppressWarnings("ALL")
@RunWith(MockitoJUnitRunner.class)
public class Misuse10Test {

    @Mock
    private UserService userService;

    @Mock
    private DivisionRepository divisionRepository;

    private DivisionEntity divisionEntity;
    private Division division;
    private ProcessEntity processEntity;

    @Before
    public void setUp() {
        processEntity = new ProcessEntity();

        division = new Division();
        division.setId(1L);

        divisionEntity = new DivisionEntity();
        divisionEntity.setId(1L);

        when(userService.currentUserDivision()).thenReturn(Optional.of(division));
        when(divisionRepository.findRegionOfDivision(1L)).thenReturn(Optional.of(divisionEntity));
    }

    private void misuse() {
        Optional<Division> userDivision = userService.currentUserDivision();

        processEntity.setDivision(
                userDivision.isPresent()
                        ? divisionRepository.findRegionOfDivision(userDivision.get().getId()).orElse(null) : null);
    }

    private void useProperly() {
        Optional<Division> divisionOpt = userService.currentUserDivision();

        divisionOpt.flatMap(localDivision -> divisionRepository.findRegionOfDivision(localDivision.getId()))
                .ifPresent(processEntity::setDivision);
    }

    @Test
    public void test_case10_misuse() {
        // WHEN
        misuse();

        // THEN
        assertThat(processEntity.getDivision(), is(divisionEntity));
    }

    @Test
    public void test_case10_properuse() {
        // WHEN
        useProperly();

        // THEN
        assertThat(processEntity.getDivision(), is(divisionEntity));
    }
}