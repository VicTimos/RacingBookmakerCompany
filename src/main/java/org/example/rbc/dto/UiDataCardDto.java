package org.example.rbc.dto;

import java.util.Map;

public class UiDataCardDto extends UiElementDto {

    private String name;
    private Map<String, String> value;
    private String imageBase64;

    public UiDataCardDto(String name, Map<String, String> value, String imageBase64) {
        super();
        this.name = name;
        this.value = value;
        this.imageBase64 = imageBase64;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Map<String, String> getValue() {
        return value;
    }

    public void setValue(Map<String, String> value) {
        this.value = value;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }
}