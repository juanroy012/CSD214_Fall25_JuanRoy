package Lab3.pojos;

import java.io.Serializable;

import static Lab2.Prompt.print;

/**
 * DTO for {@link Lab3.entities.TicketEntity}
 */
public class Ticket extends Editable implements SaleableItem, Serializable {
    private String description;
    private Double price;

    @Override
    public String toString() {
        return  "Description: " + description +
                ", Price: " + price;
    }

    public Ticket() {
        this.description = "default description";
        this.price = 0.0;
    }

    public Ticket(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void initialize() {
        try {
            print("Enter Description: ");
            description = getInput(description);
            print("Enter Price: ");
            price = getInput(price);
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }
    }

    @Override
    public void edit() {
        try {
            print("Enter Description: ");
            String newDescription = getInput(description);
            if(!newDescription.isBlank()) { description = newDescription; }

            print("Enter Price: ");
            String newPrice = getInput(String.valueOf(price));
            if(!newPrice.isBlank()) { price = Double.parseDouble(newPrice); }

        } catch (Exception e) {
            System.out.println("Enter only numbers on the list!");
        }
    }

    @Override
    public void sellItem() {
        System.out.println("Ticket with the Description: " + description + "\n" +
                "Has been sold for: " + getPrice());
    }
}
