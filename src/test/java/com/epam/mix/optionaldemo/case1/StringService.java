package com.epam.mix.optionaldemo.case1;

import java.util.Optional;

public class StringService {

    public Optional<String> getString() {
        return Optional.of("str");
    }

}
