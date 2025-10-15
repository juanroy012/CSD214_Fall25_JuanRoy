package Lab2.pojos;

import java.io.Serializable;
import java.util.Objects;

public abstract class Publication extends Editable implements SaleableItem, Serializable {

    protected String title;
    protected double price;
    protected int copies;
    protected String author;
    protected String isbn;
    protected String description;

    public abstract int getCopies();

    public abstract double getPrice();

    public abstract String getAuthor();

    public Publication(){
        title = "Default Publication";
        price = -1;
        copies = -1;
    }

    public Publication(String author, String title, double price, int copies, String isbn, String description) {
        this.title = title;
        this.price = price;
        this.copies = copies;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                '}';
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


}
