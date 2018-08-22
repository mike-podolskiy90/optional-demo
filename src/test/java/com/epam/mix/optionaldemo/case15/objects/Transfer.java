package com.epam.mix.optionaldemo.case15.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    private Integer amount;

    public static Transfer of(Integer amount) {
        return new Transfer(amount);
    }
}
