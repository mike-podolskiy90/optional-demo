package com.epam.mix.optionaldemo.case12;

import com.epam.mix.optionaldemo.case12.objects.User;
import com.epam.mix.optionaldemo.case12.objects.UserEntity;
import com.epam.mix.optionaldemo.case12.objects.UserRepository;
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
public class Misuse12Test {

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        when(userRepository.find(1L)).thenReturn(Optional.of(userEntity));
    }

    private Optional<User> mapUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());

        return Optional.of(user);
    }

    private Optional<User> misuse(Long id) {
        return userRepository.find(id)
                .map(this::mapUser)
                .orElse(Optional.empty());
    }

    private User mapUserProperly(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());

        return user;
    }

    private Optional<User> properuse(Long id) {
        return userRepository.find(id)
                .map(this::mapUserProperly);
    }

    @Test
    public void test_case12_misuse() {
        // WHEN
        Optional<User> actual = properuse(1L);

        // THEN
        assertTrue(actual.isPresent());
        assertThat(actual.get().getId(), is(1L));

    }
}
