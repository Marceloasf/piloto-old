package br.com.piloto.service.user;

import br.com.piloto.domain.user.User;
import br.com.piloto.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository userRepository;

    @Test
    public void findAll() {

        User user1 = new User();
        user1.setId(UUID.randomUUID());

        User user2 = new User();
        user2.setId(UUID.randomUUID());

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(this.userRepository.findAll()).thenReturn(userList);

        List<User> result = this.service.findAll();

        verify(this.userRepository).findAll();
        verifyNoMoreInteractions(this.userRepository);

        assertThat(result).hasSize(2);
        assertThat(result).extracting(User::getId).containsExactlyInAnyOrder(user1.getId(), user2.getId());
    }

    @Test
    public void findById() {

        UUID id = UUID.randomUUID();

        when(this.userRepository.findById(id)).thenReturn(Optional.of(new User()));

        this.service.findById(id);

        verify(this.userRepository).findById(id);
        verifyNoMoreInteractions(this.userRepository);
    }

    @Test
    public void saveUser() {

        User user = new User();
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setName("FooBar");

        when(this.userRepository.saveAndFlush(user)).thenReturn(user);

        this.service.saveUser(user);

        verify(this.userRepository).saveAndFlush(user);
        verifyNoMoreInteractions(this.userRepository);
    }

    @Test
    public void updateUser() {

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("FooBar");
        user.setBirthDate(LocalDate.of(2000, 1, 1));

        User updatedUser = new User();
        updatedUser.setId(user.getId());
        updatedUser.setName("Bar");
        updatedUser.setBirthDate(LocalDate.of(1999, 1, 1));

        when(this.userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(this.userRepository.saveAndFlush(user)).thenReturn(user);

        this.service.updateUser(updatedUser);

        assertThat(user.getName()).isEqualTo(updatedUser.getName());
        assertThat(user.getBirthDate()).isEqualTo(updatedUser.getBirthDate());

        verify(this.userRepository).findById(user.getId());
        verify(this.userRepository).saveAndFlush(user);
        verifyNoMoreInteractions(this.userRepository);
    }

    @Test
    public void delete() {

        User user = new User();

        doNothing().when(this.userRepository).delete(user);

        this.service.deleteUser(user);

        verify(this.userRepository).delete(user);
        verifyNoMoreInteractions(this.userRepository);
    }
}
