package org.example.rbc.domain;

import static org.example.rbc.domain.SubCommandType.ADD;
import static org.example.rbc.domain.SubCommandType.EDIT;
import static org.example.rbc.domain.SubCommandType.NO;
import static org.example.rbc.domain.SubCommandType.REMOVE;
import static org.example.rbc.domain.SubCommandType.VIEW;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum CommandType {

    //Security
    LOGOUT(
        Set.of(NO)
    ),

    LOGIN(
        Set.of(NO)
    ),

    CABINET(
        Set.of(NO)
    ),

    //Racings
    RACING(
        Set.of(VIEW, ADD, EDIT, REMOVE)
    ), // "Racing" if called asValue()

    //Bets
    BET(
        Set.of(VIEW, ADD, EDIT, REMOVE)
    ),

    //Bet types
    BET_TYPE(
        Set.of(VIEW, ADD, EDIT, REMOVE)
    ), // betType -> BET_TYPE if called map(), 'betType' if called asKey()

    //Other
    ERROR(
        false,
        Set.of(NO)
    );

    private boolean isVisible;
    private Set<SubCommandType> subCommands;

    CommandType() {
        isVisible = true;
        subCommands = new HashSet<>();
    }

    CommandType(boolean isVisible) {
        this.isVisible = isVisible;
        this.subCommands = new HashSet<>();
    }

    CommandType(Set<SubCommandType> subCommands) {
        isVisible = true;
        this.subCommands = subCommands;
    }

    CommandType(boolean isVisible, Set<SubCommandType> subCommands) {
        this.isVisible = isVisible;
        this.subCommands = subCommands;
    }

    /**
     * Method to parse command as a string and find according enum CommandType
     *
     * @param command Input string (command), like addRacing or other
     * @return Output CommandType parsed from input string
     */
    public static CommandType map(String command) {
        return CommandType.valueOf(Arrays.stream(command
                .strip()
                .split("(?=\\p{Upper})"))
            .map(String::toUpperCase)
            .collect(Collectors.joining("_"))
        );
    }

    /**
     * Manual formatting of enum (command) as camel case, like 'betType' or 'cabinet'
     * Example: got "CAMEL_CASE_CAPITALIZED", new "camelCaseCapitalized"
     *
     * @return Formatted result (String)
     */
    public String asKey() {
        String camelCaseCapitalized = Arrays.stream(this.name().split("_"))
            .map(it -> it.charAt(0) + it.substring(1).toLowerCase())
            .reduce((a, b) -> a + b)
            .get();
        return camelCaseCapitalized.substring(0, 1).toLowerCase() + camelCaseCapitalized.substring(1);
    }

    /**
     * Manual format as displayed text on the UI. If BET_TYPE passed, so the command on the UI will be
     * 'Bet type'
     *
     * @return Formatted string
     */
    public String asValue() {
        String lowerCased = this.name().toLowerCase().replace("_", " ");
        return lowerCased.substring(0, 1).toUpperCase() + lowerCased.substring(1);
    }

    /**
     * Formatting of CommandType according to Java class name conventions
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

    public boolean isVisible() {
        return isVisible;
    }
}
