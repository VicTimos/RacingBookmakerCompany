package org.example.rbc.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Map;
import org.example.rbc.domain.UniEntity;
import org.example.rbc.domain.UniField;
import org.example.rbc.domain.json.EntityMapper;
import org.example.rbc.domain.json.RootMapper;

public class FieldMapper {

    private static final String FIELDS_MAPPING_RESOURCE = "mapping.json";

    public static UniEntity getMappedEntity(Object entity, String className) {
        UniEntity uniEntity = new UniEntity();
        Map<String, UniField> fields = uniEntity.getFields();
        FieldMapper.readByClassName(className)
            .getFields()
            .forEach(f -> {
                try {
                    Field field = entity.getClass().getDeclaredField(f.getField());
                    field.setAccessible(true);
                    fields.put(
                        f.getField(),
                        new UniField(f.getText(), field.get(entity))
                    );
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                    throw new IllegalArgumentException();
                }
            });

        return uniEntity;
    }

    public static EntityMapper readByClassName(String className) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream is = ResourceManager.readInputStream(FIELDS_MAPPING_RESOURCE)) {
            return objectMapper.readValue(is, RootMapper.class)
                .getEntities()
                .stream()
                .filter(it -> it.getClassName().equals(className))
                .findFirst()
                .get();
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

}
