package org.example.rbc.dto;

public class UiTextDto extends UiElementDto {

    private String value;

    public UiTextDto(String value) {
        super();
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
