package org.example.rbc.command;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.example.rbc.domain.PageType.DATA_PAGE;
import static org.example.rbc.domain.SubCommandType.VIEW;
import static org.example.rbc.util.FieldMapper.getMappedEntity;

import java.util.Base64;
import java.util.List;
import java.util.Map;
import org.example.rbc.domain.CommandType;
import org.example.rbc.domain.UniField;
import org.example.rbc.dto.CommandDto;
import org.example.rbc.dto.UiDataCardDto;
import org.example.rbc.dto.UiTableDto;
import org.example.rbc.service.Dao;

public class ViewCommand implements Command {

    // psf fields are always declared at the start of the class
    // modifiable
    private static final Integer NUMBER_OF_COLUMNS = 4;

    private Dao service;

    public ViewCommand() {}

    @Override
    public CommandDto execute(CommandDto dto) {
        CommandType command = CommandType.valueOf(dto.getCommand());
        dto.setPage(DATA_PAGE.getValue());
        dto.addParam("title", command.asValue());
        dto.setSubCommand(VIEW.name());
        dto.addParam(
            command.name(),
            new UiTableDto(
                (List<Object>) service.findAll()
                    .stream()
                    .map(it -> {
                        Map<String, UniField> fields = getMappedEntity(it, command.asClassName())
                            .getFields();
                        return new UiDataCardDto(
                            fields.get("id").getValue().toString(),
                            fields.entrySet()
                                .stream()
                                .filter(obj -> !obj.getKey().equals("id") && !obj.getKey().equals("image"))
                                .collect(
                                    toMap(
                                        k -> k.getValue().getText(),
                                        v -> v.getValue().getValue().toString()
                                    )
                                ),
                            Base64.getEncoder().encodeToString((byte[]) fields.get("image").getValue())
                        );
                    })
                    .collect(toList()),
                NUMBER_OF_COLUMNS
            )
        );
        return dto;
    }

    public void setService(Dao service) {
        this.service = service;
    }
}
