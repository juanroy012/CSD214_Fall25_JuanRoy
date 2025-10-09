package Lab1.pojos.classes;

import java.util.Objects;

import static Lab1.pojos.Main.itemMap;
import static Lab1.pojos.ui.Prompt.*;

public class Book extends Publication {
    public String author;

    public Book() {
        author = "Default Author";
    }

    public Book (String author) {
        this.author = author;
    }

    public Book(String author, String title, double price, int copies) {
        super(title, price, copies);
        this.author = author;
    }

    @Override
    public void initialize() {
        try {
            print("Enter ID: ");
            id = getInput(id);
            print("Enter Author's name:");
            author = getInput(author);
            print("Enter Title: ");
            title = getInput(title);
            print("Enter Copies: ");
            copies = getInput(copies);
            print("Enter Price: ");
            price = getInput(price);
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
            askAgain();
        }
    }

    @Override
    public void sellItem() {
        if (getCopies() == 0) {
            print("There is no copy left for this item!");
        } else {
            copies -= 1;
            print("Book with the Title: " + title + "\n" +
                "Has been sold for: " + getPrice() + "\n" +
                "Copies left: " + getCopies());
        }
    }

    @Override
    public int getCopies() {
        return copies;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void edit() {
        try {
            print("Enter the ID of the book you'd like to edit: ");
            id = getInput(id);
            SaleableItem item = itemMap.get(id);
            if (item != null) {
                print("Enter Author's name:");
                author = getInput(author);
                print("Enter Title: ");
                title = getInput(title);
                print("Enter Copies: ");
                copies = getInput(copies);
                print("Enter Price: ");
                price = getInput(price);
            } else {
                print("Item with the ID: " + id + " is not found.");
                askAgain();
            }
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
            askAgain();
        }
    }

    @Override
    public String toString() {
        return  "Book --> " +
                " Title: " + title + '\'' +
                ", Copies: " + copies +
                ", Price: " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author);
    }

}
