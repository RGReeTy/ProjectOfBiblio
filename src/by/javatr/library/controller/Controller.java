package by.javatr.library.controller;

import by.javatr.library.controller.command.Command;
import by.javatr.library.controller.command.CommandProvider;

public final class Controller {

    private final CommandProvider provider = new CommandProvider();

    private final char paramDelimeter = ' ';

    public String executeTask(String request) {
        String commandName;
        Command executionCommand;

        commandName = request.trim();
        //commandName = request.substring(0, request.indexOf(paramDelimeter));
        executionCommand = provider.getCommand(commandName);

        String response;
        response = executionCommand.execute(request);

        return response;
    }

}
