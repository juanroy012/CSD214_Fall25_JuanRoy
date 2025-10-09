package Lab1.pojos.classes;

import static Lab1.pojos.Main.itemMap;
import static Lab1.pojos.ui.Prompt.*;


public class Ticket extends Editable implements SaleableItem, Serializable {
    public int id;
    public String description;
    public double price;

    @Override
    public String toString() {
        return  "Ticket --> " +
                ", Description: " + description + '\'' +
                ", Price: " + price;
    }

    public Ticket() {
        this.description = "default description";
        this.price = 0;
    }

    public Ticket(String description, double price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public void initialize() {
        try {
            print("Enter ID: ");
            id = getInput(id);
            print("Enter Description: ");
            description = getInput(description);
            print("Enter Price: ");
            price = getInput(price);
            Ticket newTicket = new Ticket(description, price);
            itemMap.put(id, newTicket);
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
            askAgain();
        }
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void edit() {
        try {
            print("Enter the ID of the ticket you'd like to edit: ");
            id = getInput(id);
            SaleableItem item = itemMap.get(id);
            if (item != null) {
                print("Enter Description: ");
                description = getInput(description);
                print("Enter Price: ");
                price = getInput(price);
            } else {
                print("Item with the ID: " + id + " is not found.");
            }
        } catch (Exception e) {
            System.out.println("Enter only numbers on the list!");
            askAgain();
        }
    }

    @Override
    public void sellItem() {
        System.out.println("Ticket with the Description: " + description + "\n" +
                "Has been sold for: " + getPrice());
    }

    @Override
    public double getPrice() {
        return this.price;
    }
}
