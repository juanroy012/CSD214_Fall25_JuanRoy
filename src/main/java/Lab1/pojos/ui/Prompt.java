package Lab1.pojos.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static Lab1.pojos.ui.Menu.*;

public class Prompt {

    private static final Scanner scanner = new Scanner(System.in);

    public static int promptForId() {
        System.out.println("Enter ID number: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static double promptForPrice() {
        System.out.println("Enter the price: ");
        return Double.parseDouble(scanner.nextLine());
    }

    public static int promptForCopies() {
        System.out.println("Enter the amount of copies: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int promptForOrderQty() {
        System.out.println("Enter the amount of order: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static String promptForDescription() {
        System.out.println("Enter Description: ");
        return scanner.nextLine().trim();
    }

    public static String promptForTitle() {
        System.out.println("Enter Title: ");
        return scanner.nextLine().trim();
    }

    public static String promptForAuthor() {
        System.out.println("Enter the Author's name: ");
        return scanner.nextLine().trim();
    }

    public static boolean promptForHasDisc() {
        System.out.println("Does this magazine have a disc?(yes/no) ");
        String answer = scanner.nextLine().toLowerCase();

        if (answer.equals("yes") || answer.equals("no")) {
            switch (answer) {
                case "yes":
                    return true;
                case "no":
                    return false;
            }
        } else {
            System.out.println("Please input only 'yes' or 'no'!");
            promptForHasDisc();
        }
        return false;
    }

    public static Date promptForCurrentIssue() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);

        while (true) {
            System.out.println("Enter the issue date (yyyy-mm-dd): ");
            String dateString = scanner.nextLine();
            try {
                return simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Input valid date!");
                askAgain();
            }
        }
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
