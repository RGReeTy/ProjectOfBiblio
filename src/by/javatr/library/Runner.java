package by.javatr.library;

import by.javatr.library.controller.Controller;

public class Runner {

    public static void main(String[] args) {
        Controller controller = new Controller();

        String access = controller.executeTask("SIGN_IN ");
        System.out.println(access);
        if (access.equals("Welcome")){
            System.out.println("Success");
        }

    }
}
