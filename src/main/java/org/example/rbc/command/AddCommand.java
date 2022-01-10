package org.example.rbc.command;


import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.example.rbc.domain.PageType.DATA_ADD;
import static org.example.rbc.domain.SubCommandType.ADD;
import static org.example.rbc.domain.UiInputType.TEXT;
import static org.example.rbc.util.FieldMapper.getMappedEntity;
import static org.example.rbc.util.FieldMapper.readByClassName;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.example.rbc.domain.CommandType;
import org.example.rbc.domain.UniField;
import org.example.rbc.domain.json.EntityMapper;
import org.example.rbc.dto.CommandDto;
import org.example.rbc.dto.UiInputDto;
import org.example.rbc.service.Dao;

public class AddCommand implements Command {

    private Dao service;

    public AddCommand() {}

    @Override
    public CommandDto execute(CommandDto dto) { // RACING ADD
        String command = dto.getCommand();
        dto.setPage(DATA_ADD.getValue());
        dto.addParam("title", "Add " + command.toLowerCase());
        dto.setSubCommand(ADD.name());
        List<UiInputDto> inputs = new LinkedList<>();
        readByClassName(CommandType.valueOf(command).asClassName())
            .getFields().stream()
            .filter(obj -> !obj.getField().equals("id"))
            .forEach(
                f -> inputs.add(new UiInputDto(
                    null,
                    f.getText(),
                    TEXT
                ))
            );
        dto.addParam(
            CommandType.valueOf(command).name(),
            inputs
        );
        return dto;
    }

    public void setService(Dao service) {
        this.service = service;
    }
}
