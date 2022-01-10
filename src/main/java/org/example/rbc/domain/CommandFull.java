package org.example.rbc.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommandFull {

    private CommandType commandType;
    private SubCommandType subCommandType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommandFull that = (CommandFull) o;
        return commandType == that.commandType && subCommandType == that.subCommandType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandType, subCommandType);
    }
}
