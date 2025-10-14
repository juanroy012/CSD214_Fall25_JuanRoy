package Lab1.ui;

import Lab1.pojos.*;

import static Lab1.Main.itemMap;
import static Lab1.ui.Interaction.*;
import static Lab1.ui.Prompt.*;

import java.util.Scanner;

public class Menu {

    public static final Scanner scanner = new Scanner(System.in);
    private static final CashTill cashTill = new CashTill();

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

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
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
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Enter only numbers on the list!");
            askAgain();
        }

    }

    public static void addItems() {
        System.out.println("""
                ***********************
                Add an item
                1. Add Book
                2. Add Magazine
                3. Add Disc Magazine
                4. Add Ticket
                99. Exit
                ***********************
                Enter Choice: \s""");

        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice >= 1 && choice <= 4 || choice == 99) {
                switch (choice) {
                    case 1 :
                        Book book = new Book();
                        book.initialize();
                        itemMap.put(book.id, book);
                        break;
                    case 2 :
                        Magazine magazine = new Magazine();
                        magazine.initialize();
                        itemMap.put(magazine.id, magazine);
                        break;
                    case 3 :
                        DiscMag discMag = new DiscMag();
                        discMag.initialize();
                        itemMap.put(discMag.id, discMag);
                        break;
                    case 4 :
                        Ticket ticket = new Ticket();
                        ticket.initialize();
                        itemMap.put(ticket.id, ticket);
                        break;
                    case 99 :
                        mainMenu();
                } mainMenu();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Enter only numbers on the list!");
            askAgain();
        }
    }

    public static void editItems() {
        System.out.println("""
                ***********************
                Edit an item
                1. Edit Book
                2. Edit Magazine
                3. Edit Disc Magazine
                4. Edit Ticket
                99. Exit
                ***********************
                Enter Choice \s""");

        int choice = Integer.parseInt(scanner.nextLine());
        Editable item = (Editable) itemMap.get(choice);
        if (item != null) {
            editItem(item);
        } else {
            print("Item doesn't exist");
        }

    }

    public static void editItem(Editable item) {
        item.edit();
    }

    public static void deleteItems() {
        System.out.println("Enter the item ID you want to delete: ");
        deleteItem();
        askAgain();
    }

    public static void sellItems() {
        System.out.println("Enter the item ID you want to sell: ");
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            SaleableItem item = itemMap.get(choice);
            cashTill.sellItem(item);
            askAgain();
        } catch (Exception e) {
            System.out.println("Enter only numbers on the list!");
            askAgain();
        }
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
