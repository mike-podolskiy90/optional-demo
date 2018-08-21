package com.epam.mix.optionaldemo.case10.objects;

import java.util.Optional;

public class UserService {

    public Optional<Division> currentUserDivision() {
        throw new UnsupportedOperationException("UserService#currentUserDivision");
    }
}
