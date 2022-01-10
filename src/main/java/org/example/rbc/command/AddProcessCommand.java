package org.example.rbc.command;

import static org.example.rbc.domain.PageType.DATA_PAGE;
import static org.example.rbc.domain.SubCommandType.ADD;
import static org.example.rbc.domain.SubCommandType.EDIT;
import static org.example.rbc.domain.SubCommandType.VIEW;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Map;
import org.example.rbc.domain.SubCommandType;
import org.example.rbc.domain.UniEntity;
import org.example.rbc.domain.UniField;
import org.example.rbc.dto.CommandDto;
import org.example.rbc.exception.CommonIncorrectException;
import org.example.rbc.exception.IncorrectEntityDeclarationException;
import org.example.rbc.service.Dao;
import org.example.rbc.util.FieldMapper;

public class AddProcessCommand implements Command {

    private Dao service;

    public AddProcessCommand() {}

    @Override
    public CommandDto execute(CommandDto dto) {
        try {
            Object newEntity = ((Class<?>)
                ((ParameterizedType) (service.getClass().getGenericSuperclass()))
                    .getActualTypeArguments()[0])
                .getDeclaredConstructor()
                .newInstance();

            Map<String, Object> params = dto.getParams();

            params.forEach((key, value) -> {
                UniEntity uniEntity = FieldMapper.getMappedEntity(
                    newEntity,
                    newEntity.getClass().getSimpleName()
                );

                Arrays.stream(
                        newEntity.getClass().getDeclaredFields()
                    )
                    .filter(f -> !f.getName().equals("id") && !f.getName().equals("image"))
                    .forEach(field -> {
                        UniField uniField = uniEntity.getFields()
                            .entrySet()
                            .stream()
                            .filter(f -> f.getKey().equals(key))
                            .findFirst()
                            .get()
                            .getValue();
                        field.setAccessible(true);
                        try {
                            field.set(
                                uniField.getValue(),
                                value
                            );
                        } catch (IllegalAccessException e) {
                            try {
                                throw new CommonIncorrectException(e);
                            } catch (CommonIncorrectException ex) {
                                ex.getMessage();
                            }
                        }
                    });
            });

            service.save(newEntity);

            dto.setPage(DATA_PAGE.getValue());
            dto.setSubCommand(VIEW.name());
            dto.addParam("msg", newEntity.getClass().getSimpleName() + " was saved successfully!");
        } catch (InstantiationException
            | IllegalAccessException
            | InvocationTargetException
            | NoSuchMethodException e) {
            try {
                throw new IncorrectEntityDeclarationException(e);
            } catch (IncorrectEntityDeclarationException ex) {
                ex.getMessage();
            }
        }
        return dto;
    }

    public void setService(Dao service) {
        this.service = service;
    }
}
