package com.epam.mix.optionaldemo.case12.objects;

import java.util.Optional;

public class UserRepository {

    public Optional<UserEntity> find(Long id) {
        throw new UnsupportedOperationException("UserRepository#find");
    }
}
