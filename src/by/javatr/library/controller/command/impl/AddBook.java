package by.javatr.library.controller.command.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.controller.command.Command;
import by.javatr.library.dao.exception.DAOException;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.factory.ServiceFactory;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddBook implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = null;
        try {
            clientService = serviceFactory.getClientService();
        } catch (FileNotFoundException | DAOException e) {
            e.printStackTrace();
        }

        if (clientService != null) {
            try {
                clientService.addNewBook();
                for (Book book : clientService.returnCollectionOfBooks()) {
                    System.out.println(book);
                    response += book.toString() + "\n";
                }
            } catch (IOException | DAOException e) {
                e.printStackTrace();
            }
        } else response = "Error during load book's library procedure";

        return response;
    }
}
