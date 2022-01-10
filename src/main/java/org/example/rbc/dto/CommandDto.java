package org.example.rbc.dto;

import java.util.HashMap;
import java.util.Map;

public class CommandDto {

    private String command;
    private String subCommand;
    private String page;
    private Map<String, Object> params;

    public CommandDto() {}

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getSubCommand() {
        return subCommand;
    }

    public void setSubCommand(String subCommand) {
        this.subCommand = subCommand;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Object getValue(String key) {
        return params.get(key);
    }

    public void addParam(String key, Object value) {
        params.put(key, value);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void init() {
        if (this.params == null) {
            this.params = new HashMap<>();
        }
    }
}
