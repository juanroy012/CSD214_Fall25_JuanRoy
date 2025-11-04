package Lab3.pojos;

import java.io.Serializable;
import java.util.Objects;

import static Lab2.Prompt.print;

/**
 * DTO for {@link Lab3.entities.PencilEntity}
 */
public class Pencil extends Editable implements SaleableItem, Serializable {
    private int quantity;
    private Double price;
    private String description;

    public Pencil() {}

    public Pencil(String description, int quantity, double price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
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

            print("Enter Quantity: ");
            String newQuantity = getInput(String.valueOf(quantity));
            if(!newQuantity.isBlank()) { quantity = Integer.parseInt(newQuantity); }

        } catch (Exception e) {
            System.out.println("Enter only numbers on the list!");
        }
    }

    @Override
    public void initialize() {
        try {
            print("Enter Description: ");
            description = getInput(description);
            print("Enter Price: ");
            price = getInput(price);
            print("Enter Quantity: ");
            quantity = getInput(quantity);
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }
    }

    @Override
    public void sellItem() {
        if (getQuantity() <= 0){
            print("There is no copy left for that item");
        } else {
            quantity -= 1;
            System.out.println("Ticket with the Description: " + description + "\n" +
                    "Has been sold for: " + getPrice() + "\n" +
                    "Quantity left: " + (getQuantity()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pencil pencil)) return false;
        return quantity == pencil.quantity && Objects.equals(price, pencil.price) && Objects.equals(description, pencil.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, price, description);
    }

    @Override
    public String toString() {
        return  "Description: " + description +
                ", Quantity: " + quantity +
                ", Price: " + price;
    }
}
