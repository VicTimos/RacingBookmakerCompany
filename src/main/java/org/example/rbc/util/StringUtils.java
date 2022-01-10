package org.example.rbc.util;

import java.util.Arrays;
import org.example.rbc.exception.DomainValidationException;

public class StringUtils {

    public static String toCamelCase(String object) throws DomainValidationException {
        String camelCaseCapitalized = Arrays.stream(object.split(" "))
            .map(it -> it.charAt(0) + it.substring(1).toLowerCase())
            .reduce((a, b) -> a + b)
            .orElseThrow(DomainValidationException::new);
        return camelCaseCapitalized.substring(0, 1).toLowerCase() + camelCaseCapitalized.substring(1);
    }
}
