package Lab3.entities;

import Lab2.pojos.SaleableItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class CashTillEntity implements SaleableItem {
    @Id
    private Long id;

    @Column(name = "running_total")
    private Double runningTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRunningTotal() {
        return runningTotal;
    }

    public void setRunningTotal(Double runningTotal) {
        this.runningTotal = runningTotal;
    }

    public void sellItem(Lab3.pojos.SaleableItem item) {
        item.sellItem();
        runningTotal += item.getPrice();
    }

    @Override
    public void sellItem() {

    }

    @Override
    public double getPrice() {
        return 0;
    }
}