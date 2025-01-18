package es.ulpgc.software;

import es.ulpgc.software.controller.commands.CommandFactory;

public class Main {
    public static void main(String[] args) {
        CommandFactory commandFactory = new CommandFactory();
        new WorkingDaysService(8080, commandFactory).start();
    }
}
