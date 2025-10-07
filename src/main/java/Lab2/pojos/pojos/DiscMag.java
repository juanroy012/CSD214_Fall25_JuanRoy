package Lab2.classes;

import java.util.Date;

public class DiscMag extends Magazine {

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
    public void edit(){
        super.edit();
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
