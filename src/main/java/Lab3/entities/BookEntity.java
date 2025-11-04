package Lab3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

import static Lab2.Prompt.print;

@Entity
@Table(name = "book_table")
public class BookEntity extends PublicationEntity {

    public BookEntity() {}

    public BookEntity(String author, String title, double price, int copies, String isbn, String description) {
        super(author, title, price, copies, isbn, description);
    }

    @Override
    public String toString() {
        return  super.getDescription() +
                ", Title: " + super.getTitle() +
                ", Copies: " + super.getCopies() +
                ", Price: " + super.getPrice() +
                ", ISBN: " + super.getIsbn();
    }

    @Override
    public void sellItem() {
        if (getCopies() <= 0){
            print("There is no copy left for that item");
        } else {
            super.setCopies(super.getCopies() - 1);
            System.out.println("Book with the Title: " + super.getTitle() + "\n" +
                    "Has been sold for: " + getPrice() + "\n" +
                    "Copies left: " + (getCopies()));
        }
    }

    @Override
    public void edit() {
        try {
            print("Enter Description: ");
            String newDescription = getInput(super.getDescription());
            if (!newDescription.isBlank()) super.setDescription(newDescription);

            print("Enter Author's name:");
            String newAuthor = getInput(super.getAuthor());
            if (!newAuthor.isBlank()) { super.setAuthor(newAuthor); }

            print("Enter Title: ");
            String newTitle = getInput(super.getTitle());
            if(!newTitle.isBlank()) { super.setTitle(newTitle); }

            print("Enter Copies: ");
            String newCopies = getInput(String.valueOf(super.getCopies()));
            if (!newCopies.isBlank()) super.setCopies(Integer.parseInt(newCopies));

            print("Enter Price: ");
            String newPrice = getInput(String.valueOf(super.getPrice()));
            if (!newPrice.isBlank()) super.setPrice(Double.parseDouble(newPrice));

            print("Enter ISBN: ");
            String newIsbn = getInput(super.getIsbn());
            if (!newIsbn.isBlank()) super.setIsbn(newIsbn);

        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }
    }

    @Override
    public void initialize() {
        try {
            print("Enter Author's name:");
            super.setAuthor(getInput(super.getAuthor()));
            print("Enter Title: ");
            super.setTitle(getInput(super.getTitle()));
            print("Enter Copies: ");
            super.setCopies(getInput(super.getCopies()));
            print("Enter Price: ");
            super.setPrice(getInput(super.getPrice()));
            print("Enter ISBN: ");
            super.setIsbn(getInput(super.getIsbn()));
            print("Enter Description: ");
            super.setDescription(getInput(super.getDescription()));
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }
    }
}