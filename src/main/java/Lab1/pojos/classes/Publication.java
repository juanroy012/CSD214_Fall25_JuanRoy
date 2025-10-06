package Lab1.pojos.classes;

import java.util.Objects;

public abstract class Publication extends Editable implements Serializable, SaleableItem {

    public String title;
    public double price;
    public int copies;
    public int id;

    public abstract double getPrice();

    public Publication(){
        title = "Default Publication";
        price = -1;
        copies = -1;
    }

    public Publication(String title, double price, int copies) {
        this.title = title;
        this.price = price;
        this.copies = copies;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Publication that)) return false;
        return Double.compare(price, that.price) == 0 && copies == that.copies && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, price, copies);
    }

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                '}';
    }
}
