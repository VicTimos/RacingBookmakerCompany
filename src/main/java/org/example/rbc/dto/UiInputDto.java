package org.example.rbc.dto;

import org.example.rbc.domain.UiInputType;
import org.example.rbc.exception.DomainValidationException;
import org.example.rbc.util.StringUtils;

public class UiInputDto extends UiElementDto {

    private Object value;
    private String name;
    private String type;

    public UiInputDto(Object value, String name, UiInputType type) {
        this.value = value;
        this.name = name;
        this.type = type.name().toLowerCase();
    }

    public String nameAsKey() throws DomainValidationException {
        return StringUtils.toCamelCase(this.name);
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
