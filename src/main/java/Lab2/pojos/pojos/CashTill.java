package Lab2.classes;

public class CashTill implements SaleableItem {


    public void sellItem() {

    }

    public void sellItem(SaleableItem item) {
        item.sellItem();
    }


    public double getPrice() {
        return 0.0;
    }
}
