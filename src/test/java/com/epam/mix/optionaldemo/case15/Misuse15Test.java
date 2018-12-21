package com.epam.mix.optionaldemo.case15;

import com.epam.mix.optionaldemo.case15.objects.Man;
import com.epam.mix.optionaldemo.case15.objects.StaffService;
import com.epam.mix.optionaldemo.case15.objects.Transfer;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Misuse15Test {

    private StaffService staffService;

    @Before
    public void setUp() {
        staffService = new StaffService();
    }

    private Optional<Integer> misuse() {
        List<Man> staff = staffService.getStaff();

        if (!staff.isEmpty()) {
            return Optional.ofNullable(
                    staff.get(staff.size() - 1).getSalary()
                            .stream()
                            .map(Transfer::getAmount)
                            .reduce(0, (a, b) -> a + b));
        } else {
            return Optional.empty();
        }
    }

    private Optional<Integer> misuseButBetter() {
        List<Man> staff = staffService.getStaff();

        if (!staff.isEmpty()) {
            return staff.get(staff.size() - 1).getSalary()
                            .stream()
                            .map(Transfer::getAmount)
                            .reduce((a, b) -> a + b);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Integer> useProperly() {
        List<Man> staff = staffService.getStaff();

        return getLast(staff)
                .map(Man::getSalary)
                .map(this::calculateIncome);
    }

    private <T> Optional<T> getLast(List<T> list) {
        return Optional.of(list)
                .filter(CollectionUtils::isNotEmpty)
                .map(listParam -> listParam.get(listParam.size() - 1));
    }

    private Integer calculateIncome(List<Transfer> transferList) {
        return transferList.stream()
                .map(Transfer::getAmount)
                .reduce(0, (amount1, amount2) -> amount1 + amount2);
    }

    @Test
    public void test_case15_misuse() {
        // WHEN
        Optional<Integer> actual = useProperly();

        // THEN
        assertTrue(actual.isPresent());
        assertThat(actual.get(), is(100));
    }
}
