package Lab1.pojos.classes;

import java.util.Date;
import java.util.Scanner;

public class Ticket extends Editable implements SaleableItem, Serializable {
    private String description;
    private double price;

    @Override
    public void edit() {

    }

    @Override
    public void initialize() {}

    @Override
    public void sellItem() {}

    @Override
    public void getPrice() {

    }


}
