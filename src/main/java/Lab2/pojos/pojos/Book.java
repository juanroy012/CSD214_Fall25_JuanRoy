package Lab2.classes;

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
            id = promptForId();
            author = promptForAuthor();
            title = promptForTitle();
            copies = promptForCopies();
            price = promptForPrice();
            Book book = new Book (author, title, price, copies);
            itemMap.put(id, book);
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
            askAgain();
        }
    }

    @Override
    public void sellItem() {
        System.out.println("Book with the Title: " + title + "\n" +
                "Has been sold for: " + getPrice());
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
            id = promptForId();
            author = promptForAuthor();
            title = promptForTitle();
            copies = promptForCopies();
            price = promptForPrice();
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
            askAgain();
        }

        Book newBook = new Book(author, title, price, copies);
        itemMap.replace(id, newBook);
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
