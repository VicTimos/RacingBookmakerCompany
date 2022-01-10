package org.example.rbc.service;

import static org.example.rbc.exception.Reason.C407;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.rbc.domain.User;
import org.example.rbc.exception.AuthException;
import org.example.rbc.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User findById(Long id) {
        return repository.findById(id)
            .orElseThrow(EntityNotFoundException::new);
    }

    public User getAnonymous() {
        return findById(1L);
    }

    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        repository.findAll().forEach(userList::add);
        return userList;
    }

    public User findByName(String name) throws AuthException {
        return repository.findUserByName(name).orElseThrow(() -> new AuthException(C407));
    }

    public User save(User user) {
        return repository.save(user);
    }

    public List<User> saveAll(List<User> races) {
        List<User> userList = new ArrayList<>();
        repository.saveAll(races).forEach(userList::add);
        return userList;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
