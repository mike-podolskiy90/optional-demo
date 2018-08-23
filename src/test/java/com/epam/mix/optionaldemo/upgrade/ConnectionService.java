package com.epam.mix.optionaldemo.upgrade;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ConnectionService {

    public Optional<String> untrustedConnection() {
        return Optional.empty();
    }

    public Optional<String> goodConnect() {
        return Optional.of("good connection!");
    }

}
