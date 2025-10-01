package Lab1.pojos.ui;

import static Lab1.pojos.ui.Interaction.*;
import static Lab1.pojos.ui.Prompt.*;

import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {

        System.out.print("""
                ***********************
                1. Add Items
                2. Edit Items
                3. Delete Items
                4. Sell Items
                5. List Items
                99. Quit
                ***********************
                Enter Choice:\s""");

        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= 5 || choice == 99) {
                switch (choice) {
                    case 1 -> addItems();
                    case 2 -> editItems();
                    case 3 -> deleteItems();
                    case 4 -> sellItems();
                    case 5 -> listItems();
                    case 99 -> exit();
                }
            } else {
                System.out.println("Enter only numbers on the list!");
                askAgain();
            }
        } catch (Exception e) {
            System.out.println("Enter only numbers on the list!");
            askAgain();
        }
    }

    public static void addItems() {
        System.out.println("""
                Add an item
                1. Add Book
                2. Add Magazine
                3. Add DiscMag
                4. Add Ticket
                99. Exit
                """);

        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= 4 || choice == 99) {
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> addMagazine();
                    case 3 -> addDiscMag();
                    case 4 -> addTicket();
                    case 99 -> exit();
                }
            } else {
                System.out.println("Enter only numbers on the list!");
                scanner.nextLine();
                askAgain();
            }
        } catch (Exception e) {
            System.out.println("Enter only numbers on the list!");
            scanner.nextLine();
            askAgain();
        }
    }

    public static void editItems() {
        System.out.println("""
                Edit an item
                1. Sell Book
                2. Sell Magazine
                3. Sell DiscMag
                4. Sell Ticket
                99. Exit
                """);

        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= 4 || choice == 99) {
                switch (choice) {
                    case 1 -> editBook();
                    case 2 -> editMagazine();
                    case 3 -> editDiscMag();
                    case 4 -> editTicket();
                    case 99 -> exit();
                }
            } else {
                System.out.println("Enter only numbers on the list!");
                scanner.nextLine();
                askAgain();
            }
        } catch (Exception e) {
            System.out.println("Enter only numbers on the list!");
            scanner.nextLine();
            askAgain();
        }
    }

    public static void deleteItems() {
        System.out.println("... Delete Items functionality will be implemented here .... ");
        askAgain();
    }

    public static void sellItems() {
        System.out.println("Enter the item ID you want to sell: ");
        Interaction.sellItem();
    }

    public static void listItems() {
        listItem();
    }

    public static void exit() {
        System.out.println("Goodbye.");
        scanner.close();
        System.exit(0);

    }
}
