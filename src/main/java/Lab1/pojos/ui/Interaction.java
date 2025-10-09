package Lab1.pojos.ui;

import Lab1.pojos.classes.*;

import java.util.Scanner;

import static Lab1.pojos.Main.itemMap;
import static Lab1.pojos.ui.Prompt.askAgain;

public class Interaction {

    private static final Scanner scanner = new Scanner(System.in);

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
