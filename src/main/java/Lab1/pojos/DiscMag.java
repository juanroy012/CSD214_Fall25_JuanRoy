package Lab1.pojos;

import java.time.LocalDate;

import static Lab1.ui.Prompt.print;

public class DiscMag extends Magazine{

    public boolean hasDisc;

    public DiscMag() {
        hasDisc = false;
    }

    public DiscMag(boolean hasDisc, LocalDate currentIssue, String title, double price, int copies, int orderQty) {
        super(currentIssue, title, price, copies, orderQty);
        this.hasDisc = hasDisc;
    }

    @Override
    public void initialize() {
        super.initialize();
        print("Has Disc: ");
        hasDisc = getInput(hasDisc);
    }

    @Override
    public void sellItem() {
        super.sellItem();
        print("Has Disc: " + hasDisc);
    }

    @Override
    public void edit(){
        super.edit();
        print("Has Disc: ");
        hasDisc = getInput(hasDisc);
    }

    @Override
    public String toString() {
        return  "Disc Magazine --> " +
                "Current Issue: " + currentIssue + '\'' +
                ", Title: " + title +
                ", Price: " + price +
                ", Copies: " + copies +
                ", OrderQty: " + orderQty;
    }
}
