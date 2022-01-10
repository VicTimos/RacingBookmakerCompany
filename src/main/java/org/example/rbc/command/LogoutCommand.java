package org.example.rbc.command;

import org.example.rbc.application.UserCurrent;
import org.example.rbc.dto.CommandDto;
import org.example.rbc.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class LogoutCommand implements Command {

    private UserCurrent currentUser;
    private UserService service;

    public LogoutCommand(UserCurrent currentUser, UserService service) {
        this.currentUser = currentUser;
        this.service = service;
    }

    @Override
    public CommandDto execute(CommandDto dto) {
        currentUser.setUser(service.getAnonymous());
        dto.setPage("login");
        return dto;
    }
}
