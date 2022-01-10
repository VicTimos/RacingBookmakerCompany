package org.example.rbc.application;

import static org.example.rbc.domain.RoleType.ROLE_ADMIN;
import static org.example.rbc.domain.RoleType.ROLE_ANONYMOUS;

import org.example.rbc.domain.RoleType;
import org.example.rbc.domain.User;
import org.example.rbc.service.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements ApplicationRunner {

    private UserService service;

    public UserInitializer(UserService service) {
        this.service = service;
    }

    @Override
    public void run(ApplicationArguments args) {
        service.save(new User("anon", "", ROLE_ANONYMOUS));
        service.save(new User("admin", "admin", ROLE_ADMIN));
        service.save(new User("Rudolf", "1234abc", RoleType.ROLE_CLIENT));
        service.save(new User("booky", "booky158", RoleType.ROLE_BOOKMAKER));
    }
}
