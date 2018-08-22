package com.epam.mix.optionaldemo.case15.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Income {

    private Long amount;

    public static Income of(Long amount) {
        return new Income(amount);
    }
}
