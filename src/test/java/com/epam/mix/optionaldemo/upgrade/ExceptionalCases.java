package com.epam.mix.optionaldemo.upgrade;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class ExceptionalCases {

    private ConnectionService connectionService;

    @Before
    public void setUp() throws Exception {
        connectionService = new ConnectionService();
    }

    @Test
    public void test_or() {
        // попробовать переподключиться, если первая попытка провалилась
        Optional<String> firstAttempt = connectionService.badConnect();
        Optional<String> secondAttempt = connectionService.badConnect();
    }

    @Test
    public void test_ifAbsent() {
        // залогировать ошибку, если попытка провалилась
        Optional<String> connection = connectionService.badConnect();
    }

    @Test
    public void test_ifPresentOrElse() {
        // залогировать сообщение если удалось подключиться или залогировать ошибку в противном случае
        Optional<String> connection = connectionService.badConnect();
    }

    @Test
    public void test_stream() {
        // получить список подключений, проверить какие работают и залогировать
        List<Optional<String>> list = connectionService.getConnections();
    }
}
