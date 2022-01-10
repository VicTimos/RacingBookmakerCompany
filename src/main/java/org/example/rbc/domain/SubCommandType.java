package org.example.rbc.domain;

import java.util.Arrays;
import java.util.stream.Collectors;

// Sub commands for CommandType (maybe optional)
public enum SubCommandType {
    VIEW,
    ADD,
    EDIT,
    REMOVE,
    ADD_PROCESS,

    NO;

    /**
     * Formatting of SubCommandType according to Java class name conventions
     * @return formatted String
     */
    public String asClassName() {
        return Arrays.stream(
                this.name()
                    .toLowerCase()
                    .split("_")
            )
            .map(it -> it.substring(0, 1).toUpperCase() + it.substring(1))
            .collect(Collectors.joining());
    }
}
