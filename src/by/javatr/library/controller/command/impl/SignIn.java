package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;
import by.javatr.library.dao.exception.DAOException;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.exception.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class SignIn implements Command {
    @Override
    public String execute(String request) {
        String login = null;
        String password = null;
        String response = null;
        Scanner scanner = new Scanner(System.in);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = null;
        try {
            clientService = serviceFactory.getClientService();
        } catch (FileNotFoundException | DAOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Enter login");
            login = scanner.next();
            System.out.println("Enter password");
            password = scanner.next();

            if (clientService != null) {
                if (clientService.signIn(login, password)) {
                    response = "Welcome";
                } else {
                    response = "Wrong login or password!";
                }
            }
        } catch (ServiceException e) {
            response = "Error during login procedure";
        }
        return response;
    }
}
