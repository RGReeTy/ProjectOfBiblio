package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;

public class SignIn implements Command {
    @Override
    public String execute(String request) {
        String login = null;
        String password = null;

        String response = null;
        //.......................

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            clientService.signIn(login, password);
            response = "Welcome";
        } catch (ServiceException e) {
            response = "Error during login procedure";
        }

        return response;
    }
}
