package Lab1.pojos.ui;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);

    public void mainMenu() {


        System.out.print("***********************" +
                "1. Add Items" +
                "2. Edit Items" +
                "3. Delete Items" +
                "4. Sell Items" +
                "5. List Items" +
                "99. Quit" +
                "***********************" +
                "Enter Choice: ");

        int choice = scanner.nextInt();

        switch(choice) {
            case 1 ->
        }
    }
}
