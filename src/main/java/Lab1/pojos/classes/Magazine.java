package Lab1.pojos.classes;

import java.util.Date;

import static Lab1.pojos.Main.itemMap;
import static Lab1.pojos.ui.Prompt.*;

public class Magazine extends Publication{

    public int orderQty;
    public Date currentIssue;

    @Override
    public String toString() {
        return  ". Magazine --> " +
                "Current Issue: " + currentIssue + '\'' +
                ", Title: " + title +
                ", Price: " + price +
                ", Copies: " + copies +
                ", OrderQty: " + orderQty;
    }

    public Magazine() {
        orderQty = -1;
        currentIssue = new Date();
    }

    public Magazine(Date currentIssue, String title, double price, int copies, int orderQty) {
        super (title, price, copies);
        this.orderQty = orderQty;
        this.currentIssue = currentIssue;
    }


    @Override
    public void initialize() {
        boolean hasDisc;
        try {
            id = promptForId();
            currentIssue = promptForCurrentIssue();
            title = promptForTitle();
            price = promptForPrice();
            copies = promptForCopies();
            orderQty = promptForOrderQty();
            hasDisc = promptForHasDisc();

            if (!hasDisc) {
                Magazine magazine = new Magazine(currentIssue, title, price, copies, orderQty);
                itemMap.put(id, magazine);
            } else if (hasDisc) {
                DiscMag discMag = new DiscMag(hasDisc, currentIssue, title, price, copies, orderQty);
                itemMap.put(id, discMag);
            }
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }
    }

    public void setMagazineMap() {
        Magazine magazine = new Magazine(currentIssue, title, price, copies, orderQty);
        itemMap.put(id, magazine);
    }


    @Override
    public void edit() {
        try {
            id = promptForId();
            currentIssue = promptForCurrentIssue();
            title = promptForTitle();
            price = promptForPrice();
            copies = promptForCopies();
            orderQty = promptForOrderQty();
        } catch (Exception e) {
            System.out.println("Enter valid type of each element on the list!");
        }

        Magazine newMagazine = new Magazine(currentIssue, title, price, copies, orderQty);
        itemMap.replace(id, newMagazine);
    }

    @Override
    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void sellItem() {
        System.out.println("Magazine/Disc Magazine with the Title: " + title + "\n" +
                "Has been sold for: " + getPrice());
    }
}
