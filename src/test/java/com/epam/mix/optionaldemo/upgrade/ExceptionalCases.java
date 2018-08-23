package com.epam.mix.optionaldemo.upgrade;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

public class ExceptionalCases {

    private ConnectionService connectionService;
    private ConnectionService anotherConnectionService;

    @Before
    public void setUp() throws Exception {
        connectionService = new ConnectionService();
        anotherConnectionService = new ConnectionService();
    }

    @Test
    public void test_or() {
        // попробовать переподключиться, если первая попытка провалилась
        Optional<String> firstAttempt = connectionService.untrustedConnection();
        Optional<String> secondAttempt = connectionService.untrustedConnection();
    }

    @Test
    public void test_ifAbsent() {
        // залогировать ошибку, если попытка провалилась
        Optional<String> connection = connectionService.untrustedConnection();
    }

    @Test
    public void test_ifPresentOrElse() {
        // залогировать сообщение если удалось подключиться или залогировать ошибку в противном случае
        Optional<String> connection = connectionService.untrustedConnection();
    }

    @Test
    public void test_stream() {
        // есть несколько точек подключения, проверить коннект к ним
        Stream.of(connectionService, anotherConnectionService);
    }
}
