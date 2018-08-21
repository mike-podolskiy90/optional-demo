package com.epam.mix.optionaldemo.case9;

import com.epam.mix.optionaldemo.case9.objects.Command;
import com.epam.mix.optionaldemo.case9.objects.CommandEntity;
import com.epam.mix.optionaldemo.case9.objects.CommandMapper;
import com.epam.mix.optionaldemo.case9.objects.CommandRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SuppressWarnings("ALL")
@RunWith(MockitoJUnitRunner.class)
public class Misuse9Test {

    @Mock
    private CommandRepository commandRepository;

    private CommandMapper mapper;

    @Before
    public void setUp() throws Exception {
        CommandEntity commandEntity = new CommandEntity();
        commandEntity.setId(1L);
        commandEntity.setName("name");

        mapper = new CommandMapper();

        when(commandRepository.get()).thenReturn(commandEntity);
        when(commandRepository.getOptional()).thenReturn(Optional.of(commandEntity));
    }

    private Optional<Command> misuse() {
        CommandEntity commandEntity = commandRepository.get();

        if (commandEntity != null) {
            return Optional.of(mapper.map(commandEntity));
        }

        return Optional.empty();
    }

    private Optional<Command> useProperly() {
        return commandRepository.getOptional()
                .map(mapper::map);
    }

    @Test
    public void test_case9_misuse() {
        // WHEN
        Optional<Command> actual = misuse();

        // THEN
        assertTrue(actual.isPresent());
        assertThat(actual.get().getId(), is(1L));
        assertThat(actual.get().getName(), is("name"));
    }
}
