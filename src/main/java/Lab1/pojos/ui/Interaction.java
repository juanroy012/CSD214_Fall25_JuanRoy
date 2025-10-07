package Lab1.pojos.ui;

import Lab1.pojos.classes.*;

import java.util.Scanner;

import static Lab1.pojos.Main.itemMap;
import static Lab1.pojos.ui.Prompt.askAgain;

public class Interaction {

    private static final Ticket ticket = new Ticket();
    private static final Book book = new Book();
    private static final Magazine magazine = new Magazine();
    private static final DiscMag discMag = new DiscMag();
    private static final CashTill cashTill = new CashTill();
    private static final Scanner scanner = new Scanner(System.in);


    // Add Interaction
    public static void addBook() {
        book.initialize();
        askAgain();
    }

    public static void addMagazine() {
        magazine.initialize();
        askAgain();
    }

    public static void addDiscMag() {
        discMag.initialize();
        askAgain();
    }

    public static void addTicket() {
        ticket.initialize();
        askAgain();
    }


    // Edit Interaction
    public static void editBook() {
        book.edit();
        askAgain();
    }

    public static void editMagazine() {
        magazine.edit();
        askAgain();
    }

    public static void editDiscMag() {
        discMag.edit();
        askAgain();
    }

    public static void editTicket() {
        ticket.edit();
        askAgain();
    }

    public static void deleteItem(){
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            SaleableItem item = itemMap.get(choice);
            if (item != null) {
                System.out.println("Item : " + item + " has been deleted.");
                itemMap.remove(choice);
                askAgain();
            } else {
                System.out.println("Item with the ID: " + choice + " is not found.");
            }
        } catch (Exception e) {
            System.out.println("Enter only numbers on the list!");
            askAgain();
        }
    }

    // List Interaction
    public static void listItem() {
        StringBuilder listBuilder = new StringBuilder();
        for (int i : itemMap.keySet()) {
            listBuilder.append(i + ". " + itemMap.get(i).toString() + "\n");
        }
        System.out.println(listBuilder);
        askAgain();
    }
}
