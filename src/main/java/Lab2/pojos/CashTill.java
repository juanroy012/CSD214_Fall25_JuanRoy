package Lab2.pojos;

public class CashTill implements SaleableItem {

    public static Double runningTotal = 0.0;

    public CashTill() {}

    public double showTotal() {
        return runningTotal;
    }

    public void sellItem(SaleableItem item) {
        item.sellItem();
        runningTotal += item.getPrice();
    }

    @Override
    public void sellItem() {

    }

    public double getPrice() {
        return 0.0;
    }
}
