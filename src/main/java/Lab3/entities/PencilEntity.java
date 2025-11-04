package Lab3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

import static Lab3.Prompt.print;

@Entity
@Table(name = "pencil_table")
public class PencilEntity extends ProductEntity {
    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private Double price;

    public PencilEntity() {}

    public PencilEntity(String description, int quantity, double price) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PencilEntity that)) return false;
        if (!super.equals(o)) return false;
        return quantity == that.quantity && Objects.equals(price, that.price) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity, price, description);
    }

    @Override
    public String toString() {
        return  description +
                ", Quantity: " + quantity +
                ", Price: " + price;
    }

    @Column(name = "description")
    private String description;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}