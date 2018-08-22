package com.epam.mix.optionaldemo.case15.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Man {

    private List<Transfer> salary;

    public static Man of(Transfer... transfers) {
        Man man = new Man();
        man.setSalary(Arrays.asList(transfers));
        return man;
    }
}
