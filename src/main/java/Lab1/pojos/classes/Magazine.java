package Lab1.pojos.classes;

import java.time.LocalDate;

import static Lab1.pojos.Main.itemMap;
import static Lab1.pojos.ui.Prompt.*;

public class Magazine extends Publication{

    public int orderQty;
    public LocalDate currentIssue;

    @Override
    public String toString() {
        return  "Magazine --> " +
                "Current Issue: " + currentIssue + '\'' +
                ", Title: " + title +
                ", Price: " + price +
                ", Copies: " + copies +
                ", OrderQty: " + orderQty;
    }

    public Magazine() {
        orderQty = -1;
        currentIssue = LocalDate.now();
    }

    public Magazine(LocalDate currentIssue, String title, double price, int copies, int orderQty) {
        super (title, price, copies);
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
    }


    @Override
    public void initialize() {
        try {
            print("Enter ID: ");
            id = getInput(id);
            print("Enter the issue date (yyyy-mm-dd): ");
            currentIssue = getInput(currentIssue);
            print("Enter Title: ");
            title = getInput(title);
            print("Enter Price: ");
            price = getInput(price);
            print("Enter Copies: ");
            copies = getInput(copies);
            print("Enter Order Quantity: ");
            orderQty = getInput(orderQty);
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
            askAgain();
        }
    }

    @Override
    public void edit() {
        try {
            print("Enter the ID of the magazine you'd like to edit: ");
            id = getInput(id);
            SaleableItem item = itemMap.get(id);
            if (item != null) {
                print("Enter the issue date (yyyy-mm-dd): ");
                currentIssue = getInput(currentIssue);
                print("Enter Title: ");
                title = getInput(title);
                print("Enter Price: ");
                price = getInput(price);
                print("Enter Copies: ");
                copies = getInput(copies);
                print("Enter Order Quantity: ");
                orderQty = getInput(orderQty);
            } else {
                print("Item with the ID: " + id + " is not found.");
            }
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
            askAgain();
        }
    }

    @Override
    public int getCopies() {
        return copies-1;
    }

    @Override
    public void sellItem() {
        if (getCopies() == 0) {
            print("There is no copy left for that item!");
        } else {
            copies -= 1;
            System.out.println("Magazine with the Title: " + title + "\n" +
                    "Has been sold for: " + getPrice() + "\n" +
                    "Copies left: " + getCopies());
        }
    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}
