package Lab3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

import static Lab2.Prompt.print;

@Entity
@Table(name = "ticket_table")
public class TicketEntity extends ProductEntity {
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    public TicketEntity() {}

    public TicketEntity(String description, double price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TicketEntity that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(price, that.price) == 0 && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, price);
    }

    @Override
    public String toString() {
        return  description +
                ", Price: " + price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void sellItem() {
        System.out.println("Ticket with the Description: " + description + "\n" +
                "Has been sold for: " + getPrice());
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

}