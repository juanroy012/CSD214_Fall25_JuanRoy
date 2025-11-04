package Lab3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Objects;

import static Lab2.Prompt.print;

@Entity
public class DiscMagEntity extends MagazineEntity {
    @Column(name = "has_disc", nullable = true)
    private boolean hasDisc;

    public DiscMagEntity() {
        hasDisc = false;
    }

    public DiscMagEntity(String author, String title, double price, int copies, String isbn, String description, LocalDate currentIssue, boolean hasDisc) {
        super(author, title, price, copies, isbn, description, currentIssue);
        this.hasDisc = hasDisc;

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DiscMagEntity that)) return false;
        if (!super.equals(o)) return false;
        return hasDisc == that.hasDisc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hasDisc);
    }

    @Override
    public String toString() {
        return  "Disc " + super.toString() +
                ", Has Disc: " + hasDisc;
    }

    public boolean getHasDisc() {
        return hasDisc;
    }

    public void setHasDisc(boolean hasDisc) {
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
}