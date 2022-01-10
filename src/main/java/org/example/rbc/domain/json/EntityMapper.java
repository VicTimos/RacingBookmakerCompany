package org.example.rbc.domain.json;

import java.util.List;
import lombok.Data;

@Data
public class EntityMapper {
    private String className;
    private List<FieldMapper> fields;
}
