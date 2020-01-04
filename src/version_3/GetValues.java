package version_3;

import java.util.Scanner;

public class GetValues {

    public static int getIntValue(String message) {
        return getIntValue(message, false, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    public static int getIntValue(String message, int startLimit, int endLimitInclude) {
        return getIntValue(message, true, startLimit, endLimitInclude);

    }

    public static int getIntValue(String message, boolean isInclude, int startLimit, int endLimitInclude) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (isInclude) {
                System.out.print(message + " number from " + startLimit + " to " + endLimitInclude + "  ");

            } else {
                System.out.print(message + "  ");
            }
            int a;
            if (scanner.hasNextInt()) {
                a = scanner.nextInt();
                if (a >= startLimit && a <= endLimitInclude) {
                    return a;
                }
            } else if (scanner.hasNext()) {
                System.out.println("You have entered an invalid number, input real number");
                scanner.next();
            }
        }

    }

    public static long getLongValue(String message) {

        Scanner scanner = new Scanner(System.in);

        System.out.print(message);

        long a;
        while (true) {

            if (scanner.hasNextLong()) {
                a = scanner.nextLong();
                return a;

            } else if (scanner.hasNext()) {
                System.out.println("You have entered an invalid number, input real number");
                scanner.next();
            }
        }

    }

    public static String getStringValue(String message) {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.print(message + "  ");

            if (scanner.hasNext("\\w+")) {
                return scanner.next("\\w+");
            } else {
                System.err.println("You have entered an invalid string, input string only letters");
                scanner.nextLine();
            }
        }
    }

    public static String getStringLine(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(message + "  ");

        while (true) {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            } else {
                System.out.println("You have entered an invalid string, input string only letters");
                scanner.next();
            }
        }
    }

    public static String getStringUntilExit(String message) {

        Scanner scanner = new Scanner(System.in);

        System.out.println(message + ". \" Exit\"- close input the content");

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while (true) {
            if (scanner.hasNextLine()) {

                line = scanner.nextLine();

                if (line.equalsIgnoreCase("exit")) {
                    break;
                } else {
                    stringBuilder.append(line).append("\n");
                }

            } else {
                System.out.println("You have entered an invalid string, input string only letters");
                scanner.next();
            }
        }
        return stringBuilder.toString();

    }

}
