package br.com.piloto.repository.user;

import br.com.piloto.domain.user.User;
import br.com.piloto.test.RepositoryTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RepositoryTest
@ExtendWith(SpringExtension.class)
@Sql(scripts = {"/sql/user/user.sql"})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final UUID userId = UUID.fromString("affb9869-61b3-4100-bafd-df7cf46ef341");

    @Test
    public void findAll() {

        List<User> userList = this.userRepository.findAll();

        assertThat(userList).hasSize(3);
        assertThat(userList).extracting(User::getName).containsExactlyInAnyOrder("Teste da Silva", "Mike", "Will");
    }

    @Test
    public void findById() {

        User user = this.userRepository.findById(userId).get();

        assertThat(user.getName()).isEqualTo("Teste da Silva");
        assertThat(user.getBirthDate()).isEqualTo(LocalDate.of(2000, 1, 1));
    }

    @Test
    public void delete() {

        List<User> userListBeforeDelete = this.userRepository.findAll();

        assertThat(userListBeforeDelete).hasSize(3);

        this.userRepository.deleteById(userId);

        List<User> userListAfterDelete = this.userRepository.findAll();

        assertThat(userListAfterDelete).hasSize(2);
        assertThat(userListAfterDelete).extracting(User::getName).containsExactlyInAnyOrder("Mike", "Will");
    }

    @Test
    public void save() {

        User newUser = new User();
        newUser.setName("Cliente");
        newUser.setBirthDate(LocalDate.of(1990, 1,1));

        User persistedUser = this.userRepository.saveAndFlush(newUser);

        assertThat(persistedUser.getId()).isNotNull();
        assertThat(persistedUser.getName()).isEqualTo("Cliente");
        assertThat(persistedUser.getBirthDate()).isEqualTo(LocalDate.of(1990, 1, 1));
    }
}
