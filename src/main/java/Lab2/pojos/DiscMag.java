package Lab2.pojos;

import java.time.LocalDate;

import static Lab2.Prompt.*;

public class DiscMag extends Magazine {

    public boolean hasDisc;

    public DiscMag() {
        hasDisc = false;
    }

    public DiscMag(String author, String title, double price, int copies, String isbn, String description, LocalDate currentIssue, boolean hasDisc) {
        super(author, title, price, copies, isbn, description, currentIssue);
        this.hasDisc = hasDisc;
    }

    @Override
    public void initialize() {
        super.initialize();
        print("Has Disc: ");
        hasDisc = getInput(hasDisc);
    }

    @Override
    public void edit(){
        super.edit();

        print("Has Disc: ");
        String newHasDisc = getInput(String.valueOf(hasDisc));
        if(!newHasDisc.isBlank()) { hasDisc = Boolean.parseBoolean(newHasDisc); }
    }

    @Override
    public void sellItem() {
        if (super.getCopies() == 0) {
            print("There is no copy left for that item");
        } else {
            super.sellItem();
            print("Has Disc: " + hasDisc);
        }
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", Has Disc: " + hasDisc;
    }
}
