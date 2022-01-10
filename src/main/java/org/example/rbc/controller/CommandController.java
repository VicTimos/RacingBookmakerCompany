package org.example.rbc.controller;

import static org.example.rbc.domain.CommandType.LOGIN;
import static org.example.rbc.domain.PageType.LOGIN_PAGE;
import static org.example.rbc.domain.SubCommandType.NO;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.example.rbc.application.UserCurrent;
import org.example.rbc.command.Command;
import org.example.rbc.domain.CommandFull;
import org.example.rbc.domain.CommandType;
import org.example.rbc.domain.PageType;
import org.example.rbc.domain.RoleType;
import org.example.rbc.domain.SubCommandType;
import org.example.rbc.dto.CommandDto;
import org.example.rbc.dto.UiElementDto;
import org.example.rbc.dto.UiTextDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller that used to process all incoming commands from the UI, namely "command" string in CommandDto class
 */
@Controller // means that this controller, in spite of @RestController, used to process view data and return
// name of the pages or other controllers, not JSONs
@RequestMapping("/") // means "http://localhost:8080/"
public class CommandController {

    /**
     * Field that injected using constructor, as the next fields
     */
    private Map<CommandFull, Command> commands;
    private UserCurrent currentUser;
    private Map<RoleType, Set<CommandType>> permissions;

    /**
     * Constructor that used to inject Spring beans from context to this class (controller)
     *
     * @param commands    Injected directly as constructor argument and assigned to class commands field
     * @param currentUser The same case
     * @param permissions The same ...
     */
    public CommandController(
        Map<CommandFull, Command> commands,
        UserCurrent currentUser,
        Map<RoleType, Set<CommandType>> permissions
    ) {
        this.commands = commands;
        this.currentUser = currentUser;
        this.permissions = permissions;
    }

    /**
     * Entrance controller (context path). Temporally, used to redirect user to personal cabinet
     * @param dto Command data transfer object from previous request
     * @return Redirected resource
     */
    @GetMapping
    public ModelAndView getProcessContextPath(@ModelAttribute("dto") CommandDto dto) {
        return getProcessCommand(dto);
    }

    /**
     * Just redirects to processing POST request command if "/command" was accessed accidentally using GET request.
     * Simulation of login in the case of user logged in, redirecting to login page otherwise
     *
     * @param dto Incoming from view command dto
     * @return Redirection endpoint
     */
    @GetMapping("/command") // means http://localhost:8080/command
    public ModelAndView getProcessCommand(@ModelAttribute("dto") CommandDto dto) {
        dto.init();
        if (currentUser.isLoggedIn()) {
            dto.setCommand(LOGIN.name());
            dto.setSubCommand(NO.name());
            dto.addParam("username", currentUser.getName());
            dto.addParam("password", currentUser.getPassword());
            return postProcessCommand(dto);
        } else {
            return new ModelAndView(LOGIN_PAGE.getValue());
        }
    }

    /**
     * Method used to process POST HTTP requests on /command URL endpoint. Parsed command string from the dto and calls,
     * according to mapped enum from CommandType that have mapping in CommandSession class, command's execute method.
     *
     * @param dto Wrapper for command object
     * @return New page with set parameters
     */
    @PostMapping("/command")
    public ModelAndView postProcessCommand(@ModelAttribute("dto") CommandDto dto) {
        dto.init();
        CommandDto result = commands
            .get(new CommandFull(
                CommandType.valueOf(dto.getCommand()),
                SubCommandType.valueOf(dto.getSubCommand()))
            )
            .execute(dto);
        result.setCommand(dto.getCommand());
        result.setSubCommand(dto.getSubCommand());
        Map<String, UiElementDto> items = new HashMap<>();
        permissions.get(currentUser.getUser().getRole())
            .forEach(it -> {
                UiTextDto uiTextDto = new UiTextDto(it.asValue());
                uiTextDto.setVisible(it.isVisible());
                items.put(it.name(), uiTextDto);
            });
        dto.addParam("menu", items);
        ModelAndView modelAndView = new ModelAndView(result.getPage());
        modelAndView.addObject("dto", result);
        return modelAndView;
    }

    /**
     * Web errors handling
     * @param dto Model attribute
     * @param e Handled exception
     * @return Redirected View with attributes
     */
//    @RequestMapping("/error")
//    public ModelAndView handleError(@ModelAttribute("dto") CommandDto dto, Exception e) {
//        dto.setCommand(ERROR);
//        dto.setPage("error");
//        dto.addParam("error", e);
//        return postProcessCommand(dto);
//    }

}
