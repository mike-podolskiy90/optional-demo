package com.epam.mix.optionaldemo.upgrade;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ConnectionService {

    public Optional<String> badConnect() {
        return Optional.empty();
    }

    public Optional<String> goodConnect() {
        return Optional.of("good connection!");
    }

    public List<Optional<String>> getConnections() {
        return Arrays.asList(
                Optional.of("connection 1"),
                Optional.ofNullable(null),
                Optional.of("connection 2")
        );
    }
}
