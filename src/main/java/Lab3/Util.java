package Lab3;


import Lab3.entities.*;
import Lab3.pojos.Book;
import Lab3.pojos.DiscMag;
import Lab3.pojos.Magazine;
import Lab3.pojos.Ticket;
import com.github.javafaker.Faker;

import java.time.LocalDate;


public class Util {
    private static Faker faker = new Faker();
    private static com.github.javafaker.Book fakeBook = faker.book();
    private static com.github.javafaker.Number number = faker.number();
    private static com.github.javafaker.Code code = faker.code();
    private static com.github.javafaker.Company company = faker.company();


    public static BookEntity getFakeBook() {
        String title = fakeBook.title();
        double price = number.randomDouble(2, 10, 100);
        int copies = number.numberBetween(1, 20);
        String author = fakeBook.author();
        String isbn = code.isbn10();
        String description = "Book: " + fakeBook.genre();
        return new BookEntity(
                author,
                title,
                price,
                copies,
                isbn,
                description
        );
    }

    public static ComicBookEntity getFakeComic() {
        String title = fakeBook.title();
        double price = number.randomDouble(2, 10, 100);
        int copies = number.numberBetween(1, 20);
        String author = fakeBook.author();
        String isbn = code.isbn10();
        String description = "Book: " + fakeBook.genre();
        String illustrator = fakeBook.author();
        var random = new java.util.Random();
        return new ComicBookEntity(
                author,
                title,
                price,
                copies,
                isbn,
                description,
                illustrator,
                random.nextBoolean()
        );
    }

    public static MagazineEntity getFakeMagazine() {
        String title = fakeBook.title();
        double price = number.randomDouble(2, 10, 100);
        int copies = number.numberBetween(1, 20);
        String author = fakeBook.author();
        String isbn = code.isbn10();
        String description = "Magazine: " + fakeBook.genre();
        return new MagazineEntity(
                author,
                title,
                price,
                copies,
                isbn,
                description,
                LocalDate.now()
        );
    }

    public static DiscMagEntity getFakeDiscMag() {
        String title = fakeBook.title();
        double price = number.randomDouble(2, 10, 100);
        int copies = number.numberBetween(1, 20);
        String author = fakeBook.author();
        String isbn = code.isbn10();
        String description = "Magazine: " + fakeBook.genre();
        var random = new java.util.Random();
        return new DiscMagEntity(
                author,
                title,
                price,
                copies,
                isbn,
                description,
                LocalDate.now(),
                random.nextBoolean()
        );
    }

    public static TicketEntity getFakeTicket() {
        var random = new java.util.Random();
        return new TicketEntity(
                "This is a ticket for cool event # " + Math.abs(random.nextInt()),
                Util.getFakeDoubleBetween(1, 100)
        );
    }

    public static PencilEntity getFakePencil() {
        String description = "Pencil Brand: " + company.name();
        int quantities = number.numberBetween(1, 20);
        double price = number.randomDouble(2, 1, 10);
        return new PencilEntity(description,quantities, price);
    }


    public static int getFakeIntegerBetween(int min, int max) {
        return number.numberBetween(min, max);
    }


    public static double getFakeDoubleBetween(int min, int max) {
        return number.randomDouble(2, 10, 100);
    }


}
