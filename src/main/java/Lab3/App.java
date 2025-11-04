package Lab3;

import Lab3.entities.*;
import Lab3.pojos.*;
import jakarta.persistence.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

import static Lab2.Prompt.print;

public class App {
    private EntityManagerFactory emf;
    private Map<Long, SaleableItem> itemMap = new HashMap<>();

    private static final String menu = "\n***********************\n"
            + " 1. Add Items\n"
            + " 2. Edit Items\n"
            + " 3. Delete Items\n"
            + " 4. Sell item(s)\n"
            + " 5. List items\n"
            + "99. Quit\n"
            + "***********************\n"
            + "Enter choice: ";

    private static final String add = "\n***********************\n"
            + " 1. Add Book\n"
            + " 2. Add Comic Book\n"
            + " 3. Add Magazine\n"
            + " 4. Add Disc Magazine\n"
            + " 5. Add Ticket\n"
            + " 6. Add Pencil\n"
            + "99. Back\n"
            + "***********************\n"
            + "Enter choice: ";

    private static final String delete = "\n***********************\n"
            + " 1. Delete Book\n"
            + " 2. Delete Comic Book\n"
            + " 3. Delete Magazine\n"
            + " 4. Delete Disc Magazine\n"
            + " 5. Delete Ticket\n"
            + " 6. Delete Pencil\n"
            + "99. Back\n"
            + "***********************\n"
            + "Enter choice: ";

    private static final String edit = "\n***********************\n"
            + " 1. Edit Book\n"
            + " 2. Edit Comic Book\n"
            + " 3. Edit Magazine\n"
            + " 4. Edit Disc Magazine\n"
            + " 5. Edit Ticket\n"
            + " 6. Edit Pencil\n"
            + "99. Back\n"
            + "***********************\n"
            + "Enter choice: ";

    private static final String sell = "\n***********************\n"
            + " 1. Sell Book\n"
            + " 2. Sell Comic Book\n"
            + " 3. Sell Magazine\n"
            + " 4. Sell Disc Magazine\n"
            + " 5. Sell Ticket\n"
            + " 6. Sell Pencil\n"
            + "99. Back\n"
            + "***********************\n"
            + "Enter choice: ";

    private static final String list = "\n***********************\n"
            + " 1. List Book\n"
            + " 2. List Comic Book\n"
            + " 3. List Magazine\n"
            + " 4. List Disc Magazine\n"
            + " 5. List Ticket\n"
            + " 6. List Pencil\n"
            + " 7. List All Items\n"
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
        emf = Persistence.createEntityManagerFactory("product-pu");
        EntityManager em = emf.createEntityManager();
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
                        listProducts();
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
                print("Unknown Exception : " + e.getMessage());
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
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        print(edit);
        int choice = Integer.parseInt(input.nextLine());
        print("Enter Product ID: ");
        String productId = input.nextLine().trim();
        try {
            switch(choice) {
                case 1:
                    BookEntity book = em.createQuery("SELECT b FROM BookEntity b WHERE b.productId = :id", BookEntity.class).setParameter("id", productId).getSingleResult();
                    if (book == null) {
                        print("Book is not found!");
                        break;
                    }
                    book.edit();
                    break;
                case 2:
                    ComicBookEntity comicBook = em.createQuery("SELECT cb FROM ComicBookEntity cb WHERE cb.productId = :id", ComicBookEntity.class).setParameter("id", productId).getSingleResult();
                    if (comicBook == null) {
                        print("Comic Book is not found!");
                        break;
                    }
                    comicBook.edit();
                    break;
                case 3:
                    MagazineEntity magazine = em.createQuery("SELECT m FROM MagazineEntity m WHERE m.productId = :id", MagazineEntity.class).setParameter("id", productId).getSingleResult();
                    if (magazine == null) {
                        print("Magazine is not found!");
                        break;
                    }
                    magazine.edit();
                    break;
                case 4:
                     DiscMagEntity discMag = em.createQuery("SELECT dm FROM DiscMagEntity dm WHERE dm.productId = :id", DiscMagEntity.class).setParameter("id", productId).getSingleResult();
                    if (discMag == null) {
                        print("Disc Magazine is not found!");
                        break;
                    }
                    discMag.edit();
                    break;
                case 5:
                    TicketEntity ticket = em.createQuery("SELECT t FROM TicketEntity t WHERE t.productId = :id", TicketEntity.class).setParameter("id", productId).getSingleResult();
                    if (ticket == null) {
                        print("Ticket is not found!");
                        break;
                    }
                    ticket.edit();
                    break;
                case 6:
                    PencilEntity pencil = em.createQuery("SELECT p FROM PencilEntity p WHERE p.productId = :id", PencilEntity.class).setParameter("id", productId).getSingleResult();
                    if (pencil == null) {
                        print("Pencil is not found!");
                        break;
                    }
                    pencil.edit();
                    break;
                case 99:
                    break;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            print("Edit Item Failed!");
        } finally {
            em.close();
        }
    }

    public void deleteItem(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Query query = null;
        print(delete);
        int choice = Integer.parseInt(input.nextLine());
        print("Enter Product ID: ");
        String productId = input.nextLine().trim();

        tx.begin();
        try {
            switch (choice) {
                case 1:
                    query = em.createQuery("DELETE FROM BookEntity b WHERE b.productId = :id");
                    break;
                case 2:
                    query = em.createQuery("DELETE FROM ComicBookEntity cb WHERE cb.productId = :id");
                    break;
                case 3:
                    query = em.createQuery("DELETE FROM MagazineEntity m WHERE m.productId = :id");
                    break;
                case 4:
                    query = em.createQuery("DELETE FROM DiscMagEntity dm WHERE dm.productId = :id");
                    break;
                case 5:
                    query = em.createQuery("DELETE FROM TicketEntity t WHERE t.productId = :id");
                    break;
                case 6:
                    query = em.createQuery("DELETE FROM PencilEntity p WHERE p.productId = :id");
                    break;
                case 99:
                    break;
            }

            query.setParameter("id", productId);
            int deletedCount = query.executeUpdate();
            System.out.println("Deleted " + deletedCount + " item(s).");
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            print("Delete Item Failed!");
        } finally {
            em.close();
        }
    }

    public void populate(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            print("Populating database with products...");
            for (int i = 0; i < 2; i++) {
                em.persist(Util.getFakeBook());
                em.persist(Util.getFakeComic());
                em.persist(Util.getFakeMagazine());
                em.persist(Util.getFakeDiscMag());
                em.persist(Util.getFakeTicket());
                em.persist(Util.getFakePencil());
            }

            tx.commit();
            print("Population complete.");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            print("Populating database failed!");
        } finally {
            em.close();
        }
    }

    public void listAny(){
        StringBuilder listBuilder = new StringBuilder();
        for (Long i : itemMap.keySet()) {
            listBuilder.append(i + ". " + itemMap.get(i).toString() + "\n");
        }
        System.out.println(listBuilder);
    }

    private void listProducts() {
        EntityManager em = emf.createEntityManager();
        try {
            print(list);
            int choice = Integer.parseInt(input.nextLine());
            List<ProductEntity> results = new ArrayList<>();

            switch (choice) {
                case 1:
                    results.addAll(em.createQuery("SELECT b FROM BookEntity b WHERE TYPE(b) = BookEntity", BookEntity.class).getResultList());
                    break;
                case 2:
                    results.addAll(em.createQuery("FROM ComicBookEntity ", ComicBookEntity.class).getResultList());
                    break;
                case 3:
                    results.addAll(em.createQuery("SELECT m FROM MagazineEntity m WHERE TYPE(m) = MagazineEntity", MagazineEntity.class).getResultList());
                    break;
                case 4:
                    results.addAll(em.createQuery("FROM DiscMagEntity", DiscMagEntity.class).getResultList());
                    break;
                case 5:
                    results.addAll(em.createQuery("FROM TicketEntity", TicketEntity.class).getResultList());
                    break;
                case 6:
                    results.addAll(em.createQuery("FROM PencilEntity ", PencilEntity.class).getResultList());
                    break;
                case 7:
                    results.addAll(em.createQuery("SELECT b FROM BookEntity b WHERE TYPE(b) = BookEntity", BookEntity.class).getResultList());
                    results.addAll(em.createQuery("FROM ComicBookEntity ", ComicBookEntity.class).getResultList());
                    results.addAll(em.createQuery("SELECT m FROM MagazineEntity m WHERE TYPE(m) = MagazineEntity", MagazineEntity.class).getResultList());
                    results.addAll(em.createQuery("SELECT dm FROM DiscMagEntity dm WHERE TYPE(dm) = DiscMagEntity ", DiscMagEntity.class).getResultList());
                    results.addAll(em.createQuery("FROM TicketEntity", TicketEntity.class).getResultList());
                    results.addAll(em.createQuery("FROM PencilEntity ", PencilEntity.class).getResultList());
                    break;
                case 99:
                    break;
            }
            if (!results.isEmpty()) {
                print("Found " + results.size() + " items.");
                for (ProductEntity p : results) {
                    print(p.getProductId() + ". " + p);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong entry, try again...");
        } catch (Exception e) {
            print("Adding item failed!");
        } finally {
            em.close();
        }
    }

    public SaleableItem getItem(SaleableItem item){
        return null;
    }

    public void sellItem(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        print(sell);
        int choice = Integer.parseInt(input.nextLine());
        print("Enter Product ID: ");
        String productId = input.nextLine().trim();

        try {
            switch(choice) {
                case 1:
                    BookEntity book = em.createQuery("SELECT b FROM BookEntity b WHERE b.productId = :id", BookEntity.class).setParameter("id", productId).getSingleResult();
                    if (book == null) {
                        print("Book is not found!");
                        break;
                    }
                    book.sellItem();
                    break;
                case 2:
                    ComicBookEntity comicBook = em.createQuery("SELECT cb FROM ComicBookEntity cb WHERE cb.productId = :id", ComicBookEntity.class).setParameter("id", productId).getSingleResult();
                    if (comicBook == null) {
                        print("Comic Book is not found!");
                        break;
                    }
                    comicBook.sellItem();
                    break;
                case 3:
                    MagazineEntity magazine = em.createQuery("SELECT m FROM MagazineEntity m WHERE m.productId = :id", MagazineEntity.class).setParameter("id", productId).getSingleResult();
                    if (magazine == null) {
                        print("Magazine is not found!");
                        break;
                    }
                    magazine.sellItem();
                    break;
                case 4:
                    DiscMagEntity discMag = em.createQuery("SELECT dm FROM DiscMagEntity dm WHERE dm.productId = :id", DiscMagEntity.class).setParameter("id", productId).getSingleResult();
                    if (discMag == null) {
                        print("Disc Magazine is not found!");
                        break;
                    }
                    discMag.sellItem();
                    break;
                case 5:
                    TicketEntity ticket = em.createQuery("SELECT t FROM TicketEntity t WHERE t.productId = :id", TicketEntity.class).setParameter("id", productId).getSingleResult();
                    if (ticket == null) {
                        print("Ticket is not found!");
                        break;
                    }
                    ticket.sellItem();
                    break;
                case 6:
                    PencilEntity pencil = em.createQuery("SELECT p FROM PencilEntity p WHERE p.productId = :id", PencilEntity.class).setParameter("id", productId).getSingleResult();
                    if (pencil == null) {
                        print("Pencil is not found!");
                        break;
                    }
                    pencil.sellItem();
                    break;
                case 99:
                    break;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            print("Sell Item Failed!");
        } finally {
            em.close();
        }
    }

    public void addItem(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        print(add);
        int choice = Integer.parseInt(input.nextLine());

        try {
            tx.begin();
            switch (choice) {
                case 1 -> {
                    BookEntity book = new BookEntity();
                    book.initialize();
                    em.persist(book);
                }
                case 2 -> {
                    MagazineEntity magazine = new MagazineEntity();
                    magazine.initialize();
                    em.persist(magazine);
                }
                case 3 -> {
                    DiscMagEntity discMag = new DiscMagEntity();
                    discMag.initialize();
                    em.persist(discMag);
                }
                case 4 -> {
                    TicketEntity ticket = new TicketEntity();
                    ticket.initialize();
                    em.persist(ticket);
                }
                case 99 -> {
                }
            }
            tx.commit();
        } catch (InputMismatchException e) {
            System.out.println("Wrong entry, try again...");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            print("Adding item failed!");
        } finally {
            em.close();
        }
    }

    public List<SaleableItem> getSaleableItems() {
        return new ArrayList<>(itemMap.values());
    }

}
