package Lab1.pojos.classes;

import java.util.Scanner;

public class CashTill implements SaleableItem {

    Scanner scanner = new Scanner(System.in);

    public void sellItem(SaleableItem item) {
        item.sellItem();
    }

    @Override
    public void sellItem() {

    }

    public double getPrice() {
        return 0.0;
    }
}
