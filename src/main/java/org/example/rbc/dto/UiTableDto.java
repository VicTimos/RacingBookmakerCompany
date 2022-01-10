package org.example.rbc.dto;

import java.util.ArrayList;
import java.util.List;

public class UiTableDto extends UiElementDto {

    private List<List<Object>> value;

    public UiTableDto(List<Object> data, Integer numberOfColumns) {
        this.value = new ArrayList<>(numberOfColumns);
        List<Object> rows = new ArrayList<>();
        int rowsCounter = 1;
        for (int i = 0; i < data.size(); i++) {
            if (i / numberOfColumns == rowsCounter) {
                rowsCounter++;
                this.value.add(rows);
                rows = new ArrayList<>();
            }
            rows.add(data.get(i));
        }
        this.value.add(rows);
    }

    @Override
    public List<List<Object>> getValue() {
        return value;
    }

    public void setValue(List<List<Object>> value) {
        this.value = value;
    }
}
