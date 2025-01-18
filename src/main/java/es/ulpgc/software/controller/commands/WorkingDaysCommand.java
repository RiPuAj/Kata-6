package es.ulpgc.software.controller.commands;

import es.ulpgc.software.controller.Command;
import es.ulpgc.software.model.WorkCalendar;

import java.time.LocalDate;
import java.util.Iterator;

public class WorkingDaysCommand implements Command {

    private final Input input;
    private final Output output;

    public WorkingDaysCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() {
        Iterator<LocalDate> iterator = new WorkCalendar().iteratorFor(input.start());
        LocalDate currentDate = input.start();
        int workDays = 0;
        while (currentDate.isBefore(input.end())) {
            workDays++;
            currentDate = iterator.next();
        }

        output.workingDays(workDays);
    }

    public interface Input{
        LocalDate start();
        LocalDate end();
    }

    public interface Output{
        void workingDays(int days);
    }
}
