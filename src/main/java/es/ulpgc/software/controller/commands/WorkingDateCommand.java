package es.ulpgc.software.controller.commands;

import es.ulpgc.software.controller.Command;
import es.ulpgc.software.model.WorkCalendar;

import java.time.LocalDate;
import java.util.Iterator;

public class WorkingDateCommand implements Command {
    private final Input input;
    private final Output output;

    public WorkingDateCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        Iterator<LocalDate> localDateIterator = new WorkCalendar().iteratorFor(input.start());
        LocalDate endDate = input.start();
        for (int i = 0; i < input.workingDays(); i++) {
            endDate = localDateIterator.next();
        }
        output.end(endDate);
    }

    public interface Input {
        LocalDate start();
        int workingDays();
    }

    public interface Output {
        void end(LocalDate date);
    }

}
