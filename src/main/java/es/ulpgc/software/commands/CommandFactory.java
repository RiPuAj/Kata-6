package es.ulpgc.software.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public class CommandFactory {
    private final Map<String, Builder> builders;

    public CommandFactory(Map<String, Builder> builders) {
        this.builders = builders;
    }

    public void registerCommand(String commandName, Builder builder) {
        builders.put(commandName, builder);
    }

    public Selector buildCommandWith(HttpServletRequest request, HttpServletResponse response) {
        return name -> builders.get(name).build(request, response);
    }
}
