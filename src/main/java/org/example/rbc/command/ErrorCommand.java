package org.example.rbc.command;

import org.example.rbc.dto.CommandDto;
import org.springframework.stereotype.Component;

@Component
public class ErrorCommand implements Command {

    @Override
    public CommandDto execute(CommandDto dto) {
        // Stub
        return dto;
    }
}
