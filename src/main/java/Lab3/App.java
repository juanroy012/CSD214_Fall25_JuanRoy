package Lab3;

import Lab3.entities.*;
import Lab3.pojos.*;
import Lab3.services.ProductService;
import jakarta.persistence.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

import static Lab3.Prompt.print;

public class App {
    EntityManagerFactory emf;

    private static final String menu = """
            ***********************
             1. Add Items
             2. Edit Items
             3. Delete Items
             4. Sell item(s)
             5. List items
             6. Populate
            99. Quit
            ***********************
            Enter choice:\s""";

    private static final String add = """
            ***********************
             1. Add Book
             2. Add Comic Book
             3. Add Magazine
             4. Add Disc Magazine
             5. Add Ticket
             6. Add Pencil
            99. Back
            ***********************
            Enter choice:\s""";


    public static Scanner input;
    private final PrintStream out;
    private final ProductService productService;


    /**
     * Constructs the App with all its required dependencies.
     * @param in The input stream for user interaction (e.g., System.in).
     * @param out The output stream for displaying information (e.g., System.out).
     * @param productService The service that handles business logic.
     */
    public App(InputStream in, PrintStream out, ProductService productService) {
        this.input = new Scanner(in);
        this.out = out;
        this.productService = productService;
    }

    // Methods

    public void run(){

        boolean quit = false;
        while (!quit) {
            try {
                System.out.println(menu);
                int choice = Integer.parseInt(input.nextLine());
                switch (choice) {
                    case 1:
                        addItem();
                        break;
                    case 2:
                        editItem();
                        break;
                    case 3:
                        deleteItem();
                        break;
                    case 4:
                        sellItem();
                        break;
                    case 5:
                        listAny();
                        break;
                    case 6:
                        populate();;
                        break;
                    case 99:
                        quit = true;
                        print("***********************");
                        print("99. Quit");
                        print("Exiting application. Goodbye!");
                        break;
                    default:
                        print("Wrong entry, try again...");
                        break;
                }
            } catch (InputMismatchException e) {
                print("Wrong entry, try again...");
            } catch (Exception e) {
                print("Enter only numbers on the list!");
            }
        }
    }

    public boolean findItemExists(SaleableItem item){
        return false;
    };

    public SaleableItem findItem(SaleableItem item){

        return null;
    }

    public void editItem(){
        listAny(); // Show the user the items they can edit
        out.print("\nEnter the ID of the item to edit: ");
        String idStr = input.nextLine();
        long id;

        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            out.println("Invalid ID format. Please enter a number.");
            return;
        }

        Optional<ProductEntity> productOpt = productService.getProductById(id);

        if (productOpt.isPresent()) {
            ProductEntity productToEdit = productOpt.get();

            // Delegate the editing process to the object itself
            productToEdit.edit();

            // Save the updated object back to the repository
            productService.saveProduct(productToEdit);
            out.println("Item with ID " + id + " updated successfully.");
        } else {
            out.println("Item with ID " + id + " not found.");
        }
    }

    public void deleteItem(){
        listAny(); // Show the user the items they can edit
        out.print("\nEnter the ID of the item to delete: ");
        String idStr = input.nextLine();
        long id;

        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            out.println("Invalid ID format. Please enter a number.");
            return;
        }

        Optional<ProductEntity> productOpt = productService.getProductById(id);

        if (productOpt.isPresent()) {
            productService.deleteProduct(id);
            out.println("Item with ID " + id + " deleted successfully.");
        } else {
            out.println("Item with ID " + id + " not found.");
        }
    }

    public void populate(){
        for (int i = 0; i<2 ;i++) {
            productService.saveProduct(Util.getFakeBook());
            productService.saveProduct(Util.getFakeComic());
            productService.saveProduct(Util.getFakeMagazine());
            productService.saveProduct(Util.getFakeDiscMag());
            productService.saveProduct(Util.getFakeTicket());
            productService.saveProduct(Util.getFakePencil());
        }
    }
    public void listAny(){
        out.println("\n--- All Items in Inventory ---");
        List<ProductEntity> products = productService.getAllProducts();

        if (products.isEmpty()) {
            out.println("No items found.");
        } else {
            for (ProductEntity product : products) {
                switch (product) {
                    case ComicBookEntity comicBook ->
                            out.printf("ID: %d | Type: Comic Book   | Title: %s | Author: %s | Price: $%.2f | Copies: %d | Colorized: %b%n",
                                    comicBook.getId(), comicBook.getTitle(), comicBook.getAuthor(), comicBook.getPrice(), comicBook.getCopies(), comicBook.getColorized());
                    case BookEntity book ->
                            out.printf("ID: %d | Type: Book   | Title: %s | Author: %s | Price: $%.2f | Copies: %d%n",
                                    book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), book.getCopies());
                    case DiscMagEntity discMag ->
                            out.printf("ID: %d | Type: Disc Magazine   | Title: %s | Price: $%.2f | Copies: %d | Current Issue: %tD | Has Disc: %b%n",
                                    discMag.getId(), discMag.getTitle(), discMag.getPrice(), discMag.getCopies(), discMag.getCurrentIssue(), discMag.getHasDisc());
                    case MagazineEntity magazine ->
                            out.printf("ID: %d | Type: Magazine   | Title: %s | Price: $%.2f | Copies: %d | Current Issue: %tD%n",
                                    magazine.getId(), magazine.getTitle(), magazine.getPrice(), magazine.getCopies(), magazine.getCurrentIssue());
                    case TicketEntity ticket -> out.printf("ID: %d | Type: Ticket | Description: %s | Price: $%.2f%n",
                            ticket.getId(), ticket.getDescription(), ticket.getPrice());
                    case PencilEntity pencil -> out.printf("ID: %d | Type: Pencil | Description: %s | Quantity: %d | Price: $%.2f%n",
                            pencil.getId(), pencil.getDescription(), pencil.getQuantity(), pencil.getPrice());
                    default ->
                        // Fallback for any other product types
                            out.println(product.toString());
                }
            }
        }
        out.println("------------------------------");
    }

    public SaleableItem getItem(SaleableItem item){
        return null;
    }

    public void sellItem(){
        listAny(); // Show the user the items they can edit
        out.print("\nEnter the ID of the item to sell: ");
        String idStr = input.nextLine();
        long id;

        try {
            id = Long.parseLong(idStr);
        } catch (NumberFormatException e) {
            out.println("Invalid ID format. Please enter a number.");
            return;
        }

        Optional<ProductEntity> productOpt = productService.getProductById(id);

        if (productOpt.isPresent()) {
            productOpt.ifPresent(ProductEntity::sellItem);
            out.println("Item with ID " + id + " deleted successfully.");
        } else {
            out.println("Item with ID " + id + " not found.");
        }
    }

    public void addItem() {
        try {
            print(add);
            int choice = Integer.parseInt(input.nextLine());
            switch (choice) {
                case 1 -> {
                    BookEntity book = new BookEntity();
                    book.initialize();
                    if (!book.toString().contains("null") && book.getPrice() != 0) {
                        ProductEntity savedBook = productService.saveProduct(book);
                        out.println("Book added successfully with ID: " + savedBook.getId());
                    } else {
                        print("Adding item failed");
                    }
                }
                case 2 -> {
                    ComicBookEntity comicBook = new ComicBookEntity();
                    comicBook.initialize();
                    if (!comicBook.toString().contains("null") && comicBook.getPrice() != 0) {
                        ProductEntity savedComicBook = productService.saveProduct(comicBook);
                        out.println("Comic Book added successfully with ID: " + savedComicBook.getId());
                    } else {
                        print("Adding item failed");
                    }
                }
                case 3 -> {
                    MagazineEntity magazine = new MagazineEntity();
                    magazine.initialize();
                    if (magazine.toString().contains("null") || magazine.getPrice() != 0) {
                        ProductEntity savedMagazine = productService.saveProduct(magazine);
                        out.println("Magazine added successfully with ID: " + savedMagazine.getId());
                    } else {
                        print("Adding item failed");
                    }
                }
                case 4 -> {
                    DiscMagEntity discMag = new DiscMagEntity();
                    discMag.initialize();
                    if (discMag.toString().contains("null") || discMag.getPrice() != 0) {
                        ProductEntity savedDiscMag = productService.saveProduct(discMag);
                        out.println("DiscMag added successfully with ID: " + savedDiscMag.getId());
                    } else {
                        print("Adding item failed");
                    }
                }
                case 5 -> {
                    TicketEntity ticket = new TicketEntity();
                    ticket.initialize();
                    if (!ticket.toString().contains("null") && ticket.getPrice() != 0) {
                        ProductEntity savedTicket = productService.saveProduct(ticket);
                        out.println("Ticket added successfully with ID: " + savedTicket.getId());
                    } else {
                        print("Adding item failed");
                    }
                }
                case 6 -> {
                    PencilEntity pencil = new PencilEntity();
                    pencil.initialize();
                    if (!pencil.toString().contains("null") && pencil.getPrice() != 0) {
                        ProductEntity savedPencil = productService.saveProduct(pencil);
                        out.println("Pencil added successfully with ID: " + savedPencil.getId());
                    } else {
                        print("Adding item failed");
                    }
                }
                case 99 -> {
                    return;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong entry, try again...");
        } catch (Exception e) {
            print("Adding item failed!");
        }
    }
}
