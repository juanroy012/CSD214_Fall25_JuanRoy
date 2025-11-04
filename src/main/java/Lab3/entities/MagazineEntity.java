package Lab3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Objects;

import static Lab2.Prompt.print;

@Entity
public class MagazineEntity extends PublicationEntity {
    @Column(name = "current_issue")
    private LocalDate currentIssue;

    public MagazineEntity() {
        currentIssue = LocalDate.now();
    }

    public MagazineEntity(String author, String title, Double price, int copies, String isbn, String description, LocalDate currentIssue) {
        super (author, title, price, copies, isbn, description);
        this.currentIssue = currentIssue;
    }

    @Override
    public String toString() {
        return  super.getDescription() +
                ", Author: " + super.getAuthor() +
                ", Title: " + super.getTitle() +
                ", Price: " + super.getPrice() +
                ", Copies: " + super.getCopies() +
                ", ISBN: " + super.getIsbn() +
                ", Current Issue: " + currentIssue;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MagazineEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(currentIssue, that.currentIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), currentIssue);
    }

    public LocalDate getCurrentIssue() {
        return currentIssue;
    }

    public void setCurrentIssue(LocalDate currentIssue) {
        this.currentIssue = currentIssue;
    }

    @Override
    public void initialize() {
        try {
            print("Enter Description: ");
            super.setDescription(getInput(super.getDescription()));
            print("Enter Author: ");
            super.setAuthor(getInput(super.getAuthor()));
            print("Enter Title: ");
            super.setTitle(getInput(super.getTitle()));
            print("Enter Price: ");
            super.setPrice(getInput(super.getPrice()));
            print("Enter Copies: ");
            super.setCopies(getInput(super.getCopies()));
            print("Enter ISBN: ");
            super.setIsbn(getInput(super.getIsbn()));
            print("Enter the issue date (yyyy-mm-dd): ");
            currentIssue = getInput(currentIssue);
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }
    }

    @Override
    public void edit() {
        try {
            print("Enter Description: ");
            String newDescription = getInput(super.getDescription());
            if(!newDescription.isBlank()) { super.setDescription(newDescription); }

            print("Enter Author: ");
            String newAuthor = getInput(super.getAuthor());
            if(!newAuthor.isBlank()) { super.setAuthor(newAuthor); }

            print("Enter Title: ");
            String newTitle = getInput(super.getTitle());
            if(!newTitle.isBlank()) { super.setTitle(newTitle); }

            print("Enter Price: ");
            String newPrice = getInput(String.valueOf(super.getPrice()));
            if(!newPrice.isBlank()) { super.setPrice(Double.parseDouble(newPrice)); }

            print("Enter Copies: ");
            String newCopies = getInput(String.valueOf(super.getCopies()));
            if(!newCopies.isBlank()) { super.setCopies(Integer.parseInt(newCopies)); }

            print("Enter ISBN: ");
            String newIsbn = getInput(super.getIsbn());
            if(!newIsbn.isBlank()) { super.setIsbn(newIsbn); }

            print("Enter the issue date (yyyy-mm-dd): ");
            String newCurrentIssue = getInput(String.valueOf(currentIssue));
            if(!newCurrentIssue.isBlank()) { currentIssue = LocalDate.parse(newCurrentIssue); }

        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }
    }

    @Override
    public void sellItem() {
        if (getCopies() <= 0){
            print("There is no copy left for that item");
        } else {
            super.setCopies(super.getCopies() - 1);
            System.out.println("Magazine with the Title: " + super.getTitle() + "\n" +
                    "Has been sold for: " + getPrice() + "\n" +
                    "Copies left: " + getCopies());
        }
    }

}