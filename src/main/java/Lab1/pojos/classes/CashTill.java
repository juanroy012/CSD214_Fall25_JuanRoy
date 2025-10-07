package Lab1.pojos.classes;

import java.util.Scanner;

public class CashTill implements SaleableItem {

    Scanner scanner = new Scanner(System.in);

    public void sellItem(SaleableItem item, int choice) {
        item.sellItem(choice);
    }

    @Override
    public void sellItem(int choice) {}

    public double getPrice() {
        return 0.0;
    }
}
