package by.javatr.library;

import by.javatr.library.controller.Controller;

public class Runner {

    public static void main(String[] args) {
        Controller controller = new Controller();

        System.out.println(controller.executeTask("SIGN_IN "));
    }
}
