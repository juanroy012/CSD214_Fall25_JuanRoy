package Lab2;

import Lab2.pojos.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.UUID;

import static Lab2.Prompt.*;

public class App {
    Map<Long, SaleableItem> itemMap = new HashMap<>();

    private static final String menu = "\n***********************\n"
            + " 1. Add Items\n"
            + " 2. Edit Items\n"
            + " 3. Delete Items\n"
            + " 4. Sell item(s)\n"
            + " 5. List items\n"
            + " 6. Populate\n"
            + "99. Quit\n"
            + "***********************\n"
            + "Enter choice: ";

    private static final String add = "\n***********************\n"
            + " 1. Add Book\n"
            + " 2. Add Magazine\n"
            + " 3. Add Disc Magazine\n"
            + " 4. Add Ticket\n"
            + "99. Back\n"
            + "***********************\n"
            + "Enter choice: ";

    private static final String list = "\n***********************\n"
            + " 1. List Book\n"
            + " 2. List Magazine\n"
            + " 3. List Disc Magazine\n"
            + " 4. List Ticket\n"
            + " 5. List All Items\n"
            + "99. Back\n"
            + "***********************\n"
            + "Enter choice: ";

    public static Scanner input;
    private final PrintStream out;

    // Default constructor for normal execution
    public App() {
        this(System.in, System.out);
    }
    // Constructor for testing
    public App(InputStream in, PrintStream out) {
        this.input = new Scanner(in);
        this.out = out;
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
                        listI();
                        break;
                    case 6:
                        populate();;
                        break;
                    case 99:
                        quit = true;
                        System.out.println("***********************");
                        System.out.println("99. Quit");
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Wrong entry, try again...");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong entry, try again...");
            } catch (Exception e) {
                System.out.println("Unknown Exception : " + e.getMessage());
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
        print("Enter ID: ");
        long choice = Long.parseLong(input.nextLine());
        Editable item = (Editable) itemMap.get(choice);
        if (item != null) {
            editItem(item);
        } else {
            print("Item with the ID: " + choice + " is not found.");
        }
    }

    public void editItem(Editable item){
        item.edit();
    }

    public void deleteItem(){
        print("Enter ID: ");
        long choice = Long.parseLong(input.nextLine());
        SaleableItem item = itemMap.get(choice);
        if (item != null) {
            itemMap.remove(choice);
        } else {
            print("Item with the ID: " + choice + " is not found.");
        }
    }

    public void populate(){
        for (int i = 0; i < 2; i++) {
            addItem(Util.getFakeBook());
            addItem(Util.getFakeMagazine());
            addItem(Util.getFakeDiscMag());
            addItem(Util.getFakeTicket());
        }
    }

    public void listAny(){
        StringBuilder listBuilder = new StringBuilder();
        for (Long i : itemMap.keySet()) {
            listBuilder.append("Product ID: ")
                    .append(i)
                    .append(". ")
                    .append(itemMap.get(i).toString())
                    .append("\n");
        }
        System.out.println(listBuilder);
    }

    public SaleableItem getItem(SaleableItem item){
        return null;
    }

    public void sellItem(){
        CashTill cashTill = new CashTill();
        print("Enter ID: ");
        long choice = Long.parseLong(input.nextLine());
        SaleableItem item = itemMap.get(choice);
        if (item != null) {
            cashTill.sellItem(item);
            print("Running Total: " + cashTill.showTotal());
        } else {
            print("Item with the ID: " + choice + " is not found.");
        }
    }

    public void listI(Object item){
        Class<?> itemClass = item.getClass();
        StringBuilder listBuilder = new StringBuilder();
        boolean found = false;

        for (Long i : itemMap.keySet()) {
            Object itemType = itemMap.get(i);
            if (itemClass.isInstance(itemType)) {
                if (!found) {
                    print("Item list:\n");
                    found = true;
                }
                listBuilder.append("Product ID: ")
                        .append(i)
                        .append(". ")
                        .append(itemType)
                        .append("\n");
            }
        }
        if (!found) {
            System.out.println("No items of type " + itemClass.getSimpleName());
        } else {
            System.out.println(listBuilder);
        }
    }

    public void listI() {
        print(list);
        int choice = Integer.parseInt(input.nextLine());
        switch(choice) {
            case 1:
                listI(new Book());
                break;
            case 2:
                listI(new Magazine());
                break;
            case 3:
                listI(new DiscMag());
                break;
            case 4:
                listI(new Ticket());
                break;
            case 5:
                listAny();
                break;
            case 99:
                break;
        }
    }

    public void addItem(){
        print(add);
        int choice = Integer.parseInt(input.nextLine());

        try {
            switch (choice) {
                case 1 -> {
                    Book book = new Book();
                    book.initialize();
                    addItem(book);
                }
                case 2 -> {
                    Magazine magazine = new Magazine();
                    magazine.initialize();
                    addItem(magazine);
                }
                case 3 -> {
                    DiscMag discMag = new DiscMag();
                    discMag.initialize();
                    addItem(discMag);
                }
                case 4 -> {
                    Ticket ticket = new Ticket();
                    ticket.initialize();
                    addItem(ticket);
                }
                case 99 -> {
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong entry, try again...");
        } catch (Exception e) {
            System.out.println("Unknown Exception : " + e.getMessage());
        }
    }

    public void addItem(SaleableItem item){
        long id = (UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)/1000000000;
        switch(item) {
            case Book book:
                itemMap.put(id, book);
                break;
            case DiscMag discMag:
                itemMap.put(id, discMag);
                break;
            case Magazine magazine:
                itemMap.put(id, magazine);
                break;
            case Ticket ticket:
                itemMap.put(id, ticket);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item);
        }
    }

    public List<SaleableItem> getSaleableItems() {
        return new ArrayList<>(itemMap.values());
    }

}
