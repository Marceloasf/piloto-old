package br.com.piloto.service.user;

import br.com.piloto.domain.user.User;
import br.com.piloto.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {

        return this.repository.findAll();
    }

    public User findById(UUID id) {

        return this.repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void saveUser(User newUser) {

        this.repository.saveAndFlush(newUser);
    }

    @Transactional
    public void updateUser(User updatedUser) {

        User user = this.findById(updatedUser.getId());

        user.setName(updatedUser.getName());
        user.setBirthDate(updatedUser.getBirthDate());

        this.repository.saveAndFlush(user);
    }

    @Transactional
    public void deleteUser(User user) {

        this.repository.delete(user);
    }
}
