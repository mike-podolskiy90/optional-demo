package com.epam.mix.optionaldemo.case15.objects;

import java.util.Arrays;
import java.util.List;

public class StaffService {
    public List<Man> getStaff() {
        return Arrays.asList(
                Man.of(Transfer.of(210), Transfer.of(220), Transfer.of(230), Transfer.of(240)),
                Man.of(Transfer.of(110), Transfer.of(120), Transfer.of(130), Transfer.of(140)),
                Man.of(Transfer.of(10), Transfer.of(20), Transfer.of(30), Transfer.of(40))
        );
    }
}
