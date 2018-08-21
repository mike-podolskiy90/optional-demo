package com.epam.mix.optionaldemo.case9.objects;

import java.util.Optional;

public class CommandRepository {
    public CommandEntity get() {
        throw new UnsupportedOperationException("CommandRepository#get");
    }

    public Optional<CommandEntity> getOptional() {
        throw new UnsupportedOperationException("CommandRepository#get");
    }

}
