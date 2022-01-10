package org.example.rbc.domain.json;

import lombok.Data;

/** Mapper of fields in the class and displayed text on the frontend
 *  field - field in the class
 *  text - displayed text on the frontend
 */
@Data
public class FieldMapper {
    private String field;
    private String text;
}
