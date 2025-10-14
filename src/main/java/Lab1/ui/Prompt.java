package Lab1.ui;

import java.util.Scanner;

import static Lab1.ui.Menu.*;

public class Prompt {

    private static final Scanner scanner = new Scanner(System.in);

    public static void print(String prompt) {
        System.out.println(prompt);
    }


    public static void askAgain() {
        System.out.println("Would you like to do something else?");
        String answer = scanner.nextLine();

        if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
            switch (answer.toLowerCase()) {
                case "yes":
                    mainMenu();
                case "no":
                    scanner.close();
                    exit();
            }
        } else {
            System.out.println("Please only use 'yes' or 'no' ");
            askAgain();
        }
    }
}
