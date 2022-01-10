package org.example.rbc.domain;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UniField {
    private String text;
    private Object value;
}
