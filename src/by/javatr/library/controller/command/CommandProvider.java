package by.javatr.library.controller.command;

import by.javatr.library.controller.command.Command;
import by.javatr.library.controller.command.CommandName;
import by.javatr.library.controller.command.impl.AddBook;
import by.javatr.library.controller.command.impl.Register;
import by.javatr.library.controller.command.impl.SignIn;
import by.javatr.library.controller.command.impl.WrongRequest;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.SIGH_IN, new SignIn());
        repository.put(CommandName.REGISTRATION, new Register());
        repository.put(CommandName.ADD_BOOK, new AddBook());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    public Command getCommand(String name) {
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
