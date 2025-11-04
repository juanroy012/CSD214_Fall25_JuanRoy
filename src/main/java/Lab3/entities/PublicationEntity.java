package Lab3.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PublicationEntity extends ProductEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private double price;

    @Column(name = "copies")
    private int copies;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "description")
    private String description;

    public PublicationEntity() {};

    public PublicationEntity(String title, double price, int copies, String isbn, String description) {
        this.title = title;
        this.price = price;
        this.copies = copies;
        this.isbn = isbn;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}