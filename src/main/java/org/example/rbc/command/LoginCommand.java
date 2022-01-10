package org.example.rbc.command;

import static org.example.rbc.domain.PageType.CABINET;
import static org.example.rbc.domain.PageType.LOGIN_PAGE;
import static org.example.rbc.exception.Reason.C0;

import lombok.extern.slf4j.Slf4j;
import org.example.rbc.application.UserCurrent;
import org.example.rbc.domain.User;
import org.example.rbc.dto.CommandDto;
import org.example.rbc.exception.AuthException;
import org.example.rbc.service.UserService;
import org.springframework.stereotype.Component;

@Component // all there commands are implemented by Command interface and all have execute() method that called in CommandController
@Slf4j //create logger (see line 36)
public class LoginCommand implements Command {

    private UserCurrent currentUser;
    private UserService service;


    public LoginCommand(UserCurrent currentUser, UserService service) {
        this.currentUser = currentUser;
        this.service = service;
    }

    @Override
    public CommandDto execute(CommandDto dto) {
        String username = (String) dto.getValue("username");
        String password = (String) dto.getValue("password");
        User user;
        try {
            user = service.findByName(username);
        } catch (AuthException e) {
            log.warn(e.getMessage());
            dto.addParam(
                "msg",
                "Unfortunately, user" + username + " was not found :( (Reason: "
                    + e.getReason().name()
                    + " - "
                    + e.getReason().getMessage()
                    + ")"
            );
            dto.addParam("currentUser", service.getAnonymous());
            dto.setPage(LOGIN_PAGE.getValue());
            return dto;
        }

        if (user != null) {
            if (user.getPassword().equals(password)) {
                currentUser.setUser(user);
                dto.addParam("currentUser", user);
                dto.setPage(CABINET.getValue());
            } else {
                dto.setPage(LOGIN_PAGE.getValue());
                dto.addParam(
                    "msg",
                    "Password is incorrect, please, try again!"
                );
            }
        } else {
            dto.setPage(LOGIN_PAGE.getValue());
            dto.addParam(
                "msg",
                "Some error occurred from our side, please try again or later! (Reason: "
                    + C0.name()
                    + " - "
                    + C0.getMessage()
                    + ")"
            );
        }
        return dto;
    }
}
