package es.ulpgc.software;

import es.ulpgc.software.adapters.WorkingDateAdapter;
import es.ulpgc.software.adapters.WorkingDaysAdapter;
import es.ulpgc.software.controller.Builder;
import es.ulpgc.software.controller.commands.CommandFactory;
import es.ulpgc.software.controller.commands.WorkingDateCommand;
import es.ulpgc.software.controller.commands.WorkingDaysCommand;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WorkingDaysService {
    private final int port;
    private final CommandFactory commandFactory;
    private Javalin app;

    public WorkingDaysService(int port, CommandFactory commandFactory) {
        this.port = port;
        this.commandFactory = commandFactory;
        commandFactory.registerCommand("working-days", workingDaysBuilder());
        commandFactory.registerCommand("working-date", workingDateBuilder());
    }

    public void start() {
        app = Javalin.create()
                .get("/working-days", ctx -> execute("working-days", ctx.req(), ctx.res()))
                .get("/working-date", ctx -> execute("working-date", ctx.req(), ctx.res()))
                .get("/", ctx -> ctx.res().getWriter().write(
                        "COMANDS\n" +
                        "/working-days?start=YYYY-MM-DD&end=YYYY-MM-DD\n" +
                        "/working-date?start=YYYY-MM-DD&days=nDays"))
                .start(port);
    }

    private void execute(String commandName, HttpServletRequest req, HttpServletResponse res) {
        commandFactory.buildCommandWith(req, res).build(commandName).execute();
    }

    private void stop(){
        app.stop();
    }

    private Builder workingDateBuilder() {
       return ((req, res) ->
               new WorkingDateCommand(
                       WorkingDateAdapter.inputOf(req),
                       WorkingDateAdapter.outputOf(res)
               )
       );
    }

    private Builder workingDaysBuilder() {
        return ((req, res) ->
                new WorkingDaysCommand(
                    WorkingDaysAdapter.inputOf(req),
                    WorkingDaysAdapter.outputOf(res)
                )
        );
    }
}
