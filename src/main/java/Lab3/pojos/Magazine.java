package Lab3.pojos;

import java.time.LocalDate;

import static Lab2.Prompt.print;

/**
 * DTO for {@link Lab3.entities.MagazineEntity}
 */
public class Magazine extends Publication {

    protected LocalDate currentIssue;

    public Magazine() {
        currentIssue = LocalDate.now();
    }

    public Magazine(String author, String title, Double price, int copies, String isbn, String description, LocalDate currentIssue) {
        super (author, title, price, copies, isbn, description);
        this.currentIssue = currentIssue;
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
            print("Enter Description: ");
            description = getInput(description);
            print("Enter Author: ");
            author = getInput(author);
            print("Enter Title: ");
            title = getInput(title);
            print("Enter Price: ");
            price = getInput(price);
            print("Enter Copies: ");
            copies = getInput(copies);
            print("Enter ISBN: ");
            isbn = getInput(isbn);
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
            String newDescription = getInput(description);
            if(!newDescription.isBlank()) { description = newDescription; }

            print("Enter Author: ");
            String newAuthor = getInput(author);
            if(!newAuthor.isBlank()) { author = newAuthor; }

            print("Enter Title: ");
            String newTitle = getInput(title);
            if(!newTitle.isBlank()) { title = newTitle; }

            print("Enter Price: ");
            String newPrice = getInput(String.valueOf(price));
            if(!newPrice.isBlank()) { price = Double.parseDouble(newPrice); }

            print("Enter Copies: ");
            String newCopies = getInput(String.valueOf(copies));
            if(!newCopies.isBlank()) { copies = Integer.parseInt(newCopies); }

            print("Enter ISBN: ");
            String newIsbn = getInput(isbn);
            if(!newIsbn.isBlank()) { isbn = newIsbn; }

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
            copies -= 1;
            System.out.println("Magazine with the Title: " + title + "\n" +
                    "Has been sold for: " + getPrice() + "\n" +
                    "Copies left: " + getCopies());
        }
    }

    @Override
    public String toString() {
        return  "Description: " + description +
                ", Author: " + author +
                ", Title: " + title +
                ", Price: " + price +
                ", Copies: " + copies +
                ", ISBN: " + isbn +
                ", Current Issue: " + currentIssue;
    }

}
