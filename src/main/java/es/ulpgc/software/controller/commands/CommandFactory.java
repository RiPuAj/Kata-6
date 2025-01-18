package es.ulpgc.software.controller.commands;

import es.ulpgc.software.controller.Builder;
import es.ulpgc.software.controller.Selector;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, Builder> builders;

    public CommandFactory() {
        this.builders = new HashMap<>();
    }

    public void registerCommand(String commandName, Builder builder) {
        builders.put(commandName, builder);
    }

    public Selector buildCommandWith(HttpServletRequest request, HttpServletResponse response) {
        return name -> builders.get(name).build(request, response);
    }
}
