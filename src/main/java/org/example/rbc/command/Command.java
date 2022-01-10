package org.example.rbc.command;

import org.example.rbc.dto.CommandDto;

public interface Command {
    CommandDto execute(CommandDto dto);
}
