package Lab1.pojos.classes;

import java.util.Date;

import static Lab1.pojos.Main.itemMap;
import static Lab1.pojos.ui.Prompt.promptForHasDisc;

public class DiscMag extends Magazine{

    public boolean hasDisc;

    public DiscMag() {
        hasDisc = false;
    }

    public DiscMag(boolean hasDisc, Date currentIssue, String title, double price, int copies, int orderQty) {
        super(currentIssue, title, price, copies, orderQty);
        this.hasDisc = hasDisc;
    }

    @Override
    public void initialize() {
        super.initialize();
    }


    @Override
    public void sellItem() {
        super.sellItem();
    }

    @Override
    public String toString() {
        return  ". Disc Magazine --> " +
                "Current Issue: " + currentIssue + '\'' +
                ", Title: " + title +
                ", Price: " + price +
                ", Copies: " + copies +
                ", OrderQty: " + orderQty;
    }
}
