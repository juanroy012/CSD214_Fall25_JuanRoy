package Lab3.pojos;

import Lab3.entities.BookEntity;

import java.util.Objects;

import static Lab2.Prompt.print;

/**
 * DTO for {@link BookEntity}
 */
public class Book extends Publication {

    public Book() {
        author = "Default Author";
    }

    public Book (String author) {
        this.author = author;
    }

    public Book(String author, String title, double price, int copies, String isbn, String description) {
        super(author, title, price, copies, isbn, description);
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

    public String getAuthor() {
        return author;
    }


    @Override
    public void initialize() {
        try {
            print("Enter Author's name:");
            author = getInput(author);
            print("Enter Title: ");
            title = getInput(title);
            print("Enter Copies: ");
            copies = getInput(copies);
            print("Enter Price: ");
            price = getInput(price);
            print("Enter ISBN: ");
            isbn = getInput(isbn);
            print("Enter Description: ");
            description = getInput(description);
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }
    }

    @Override
    public void edit() {
        try {
            print("Enter Description: ");
            String newDescription = getInput(description);
            if (!newDescription.isBlank()) description = newDescription;

            print("Enter Author's name:");
            String newAuthor = getInput(author);
            if (!newAuthor.isBlank()) { author = newAuthor; }

            print("Enter Title: ");
            String newTitle = getInput(title);
            if(!newTitle.isBlank()) { title = newTitle; }

            print("Enter Copies: ");
            String newCopies = getInput(String.valueOf(copies));
            if (!newCopies.isBlank()) copies = Integer.parseInt(newCopies);

            print("Enter Price: ");
            String newPrice = getInput(String.valueOf(price));
            if (!newPrice.isBlank()) price = Double.parseDouble(newPrice);

            print("Enter ISBN: ");
            String newIsbn = getInput(isbn);
            if (!newIsbn.isBlank()) isbn = newIsbn;

        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }
    }

    @Override
    public void sellItem() {
        if (getCopies() <= 0){
            print("There is no copy left for that item");
        } else {
            copies -= 1;
            System.out.println("Book with the Title: " + title + "\n" +
                    "Has been sold for: " + getPrice() + "\n" +
                    "Copies left: " + (getCopies()));
        }
    }

    @Override
    public String toString() {
        return  "Description: " + description +
                ", Title: " + title +
                ", Copies: " + copies +
                ", Price: " + price +
                ", ISBN: " + isbn;
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
