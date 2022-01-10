package org.example.rbc.command;

import static org.example.rbc.domain.PageType.CABINET;

import org.example.rbc.dto.CommandDto;
import org.springframework.stereotype.Component;

@Component
public class CabinetCommand implements Command {

    @Override
    public CommandDto execute(CommandDto dto) {
        dto.setPage(CABINET.getValue());
        return dto;
    }
}
