package org.example.rbc.repository;

import java.util.Optional;
import org.example.rbc.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends CrudRepository<User, Long> {
    Optional<User> findUserByName(String name);
}
