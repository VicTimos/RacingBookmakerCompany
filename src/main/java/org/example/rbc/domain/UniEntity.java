package org.example.rbc.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UniEntity {

    private Map<String, UniField> fields;

    public UniEntity() {
        this.fields = new LinkedHashMap<>();
    }
}
