package org.example.rbc.application;

import static org.example.rbc.domain.CommandType.BET;
import static org.example.rbc.domain.CommandType.BET_TYPE;
import static org.example.rbc.domain.CommandType.ERROR;
import static org.example.rbc.domain.CommandType.LOGIN;
import static org.example.rbc.domain.CommandType.LOGOUT;
import static org.example.rbc.domain.CommandType.RACING;
import static org.example.rbc.domain.RoleType.ROLE_ADMIN;
import static org.example.rbc.domain.RoleType.ROLE_ANONYMOUS;
import static org.example.rbc.domain.RoleType.ROLE_BOOKMAKER;
import static org.example.rbc.domain.RoleType.ROLE_CLIENT;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.example.rbc.domain.CommandType;
import org.example.rbc.domain.RoleType;
import org.example.rbc.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class SecuritySession {

    private UserService userService;

    public SecuritySession(UserService userService) {
        this.userService = userService;
    }

    @Bean
    @SessionScope
    public UserCurrent currentUser() {
        return new UserCurrent(userService.getAnonymous());
    }

    @Bean
    @SessionScope
    public Map<RoleType, Set<CommandType>> permissions() {
        Map<RoleType, Set<CommandType>> permissions = new HashMap<>();
        permissions.put(ROLE_ANONYMOUS, Set.of(LOGIN, ERROR));
        permissions.put(
            ROLE_ADMIN,
            Set.of(
                RACING,
                LOGOUT,
                ERROR
            ));
        permissions.put(ROLE_BOOKMAKER, Set.of(BET_TYPE, LOGOUT, ERROR));
        permissions.put(ROLE_CLIENT, Set.of(BET, LOGOUT, ERROR));
        return permissions;
    }
}
