package by.javatr.library.view;

import by.javatr.library.controller.Controller;

public class Menu {
    Controller controller = new Controller();
    final String WELCOME_TEXT = "Welcome to the library!";
    final String OPTIONS_USER = "You can choose next commands:\n[SHOW]\t[FIND]\t[ADD_BOOK]\t[DELETE_BOOK]";

    public void run() {
        String access;
        System.out.println(WELCOME_TEXT);
        access = controller.executeTask("SIGN_IN ");
        if (access.equals("Welcome")) System.out.println(OPTIONS_USER);
        workWithLibrary();
    }

    public void workWithLibrary(){
        String command = controller.executeTask("SHOW ");
    }
}
