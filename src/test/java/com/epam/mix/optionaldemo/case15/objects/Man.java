package com.epam.mix.optionaldemo.case15.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Man {

    private List<Income> salary;

    public static Man of(Income... incomes) {
        // TODO: 8/22/2018 finish
        return new Man();
    }
}
