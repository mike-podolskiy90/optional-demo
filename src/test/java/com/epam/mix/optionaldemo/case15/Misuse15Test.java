package com.epam.mix.optionaldemo.case15;

import com.epam.mix.optionaldemo.case15.objects.Income;
import com.epam.mix.optionaldemo.case15.objects.Man;
import com.epam.mix.optionaldemo.case15.objects.StuffService;
import org.junit.Before;

import java.util.List;
import java.util.Optional;

public class Misuse15Test {

    private StuffService stuffService;

    @Before
    public void setUp() throws Exception {
        stuffService = new StuffService();
    }

    private Optional<Long> misuse() {
        List<Man> paymentSchedules = stuffService.getStuff();
        if (!paymentSchedules.isEmpty()) {
            return Optional.ofNullable(
                    paymentSchedules.get(paymentSchedules.size() - 1).getSalary()
                            .stream()
                            .map(Income::getAmount)
                            .reduce(0L, (a, b) -> a + b));
        } else {
            return Optional.empty();
        }
    }
}
