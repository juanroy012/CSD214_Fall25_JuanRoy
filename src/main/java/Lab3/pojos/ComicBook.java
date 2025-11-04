package Lab3.pojos;

import Lab2.Prompt;

import java.util.Objects;

import static Lab3.Prompt.print;

/**
 * DTO for {@link Lab3.entities.ComicBookEntity}
 */
public class ComicBook extends Book {
    private String illustrator;
    private boolean colorized;

    public ComicBook() {}

    public ComicBook(String author, String title, double price, int copies, String isbn, String description, String illustrator, boolean colorized) {
        super(author, title, price, copies, isbn, description);
        this.illustrator = illustrator;
        this.colorized = colorized;
    }

    public String getIllustrator() {
        return illustrator;
    }

    public boolean isColorized() {
        return colorized;
    }

    @Override
    public void initialize() {
        super.initialize();
        print("Enter Illustrator's name: ");
        illustrator = getInput(illustrator);
        print("Is it colorized: ");
        colorized = getInput(colorized);
    }

    @Override
    public void edit(){
        try {
            super.edit();

            Prompt.print("Enter ISBN: ");
            String newIllustrator = getInput(illustrator);
            if (!newIllustrator.isBlank()) illustrator = newIllustrator;

            Prompt.print("Enter ISBN: ");
            String newColorized = getInput(String.valueOf(colorized));
            if (!newColorized.isBlank()) colorized = Boolean.parseBoolean(newColorized);

        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }
    }

    @Override
    public void sellItem() {
        if (getCopies() <= 0){
            Prompt.print("There is no copy left for that item");
        } else {
            copies -= 1;
            System.out.println("Comic Book with the Title: " + title + "\n" +
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
                ", ISBN: " + isbn +
                ", Illustrator: " + illustrator +
                ", Colorized: " + colorized;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ComicBook comicBook)) return false;
        if (!super.equals(o)) return false;
        return colorized == comicBook.colorized && Objects.equals(illustrator, comicBook.illustrator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), illustrator, colorized);
    }
}
