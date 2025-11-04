package Lab3.pojos;

/**
 * DTO for {@link Lab3.entities.CashTillEntity}
 */
public class CashTill implements SaleableItem {

    private static Double runningTotal = 0.0;

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
