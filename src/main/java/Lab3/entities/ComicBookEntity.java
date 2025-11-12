package Lab3.entities;

import Lab2.Prompt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

import static Lab3.Prompt.print;

@Entity
public class ComicBookEntity extends BookEntity {
    @Column(name = "illustrator")
    private String illustrator;

    @Column(name = "colorized")
    private boolean colorized;

    public ComicBookEntity() {
        this.colorized = true;
    }

    public ComicBookEntity(String author, String title, double price, int copies, String isbn, String description, String illustrator, boolean colorized) {
        super(author, title, price, copies, isbn, description);
        this.illustrator = illustrator;
        this.colorized = colorized;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComicBookEntity that)) return false;
        if (!super.equals(o)) return false;
        return colorized == that.colorized && Objects.equals(illustrator, that.illustrator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), illustrator, colorized);
    }

    @Override
    public String toString() {
        return  "Comic " + super.toString() +
                ", Illustrator: " + illustrator +
                ", Colorized: " + colorized;
    }

    public String getIllustrator() {
        return illustrator;
    }

    public void setIllustrator(String illustrator) {
        this.illustrator = illustrator;
    }

    public boolean getColorized() {
        return colorized;
    }

    public void setColorized(boolean colorized) {
        this.colorized = colorized;
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
            super.setCopies(super.getCopies()-1);
            System.out.println("Comic Book with the Title: " + super.getTitle() + "\n" +
                    "Has been sold for: " + getPrice() + "\n" +
                    "Copies left: " + (getCopies()));
        }
    }
}