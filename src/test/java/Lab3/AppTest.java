package Lab3;

import Lab3.entities.BookEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest {

    private App app;
    private ByteArrayOutputStream outContent;
    private EntityManagerFactory emf;

    @BeforeAll
    public void setupClass() {
        // Use in-memory H2 database for testing
        emf = Persistence.createEntityManagerFactory("product-pu"); // product-pu-test points to H2
    }

    @BeforeEach
    public void setup() {
        // Capture output
        outContent = new ByteArrayOutputStream();
        app = new App(new ByteArrayInputStream("".getBytes()), new PrintStream(outContent));
        app.emf = emf; // Inject EMF
    }

    @AfterEach
    public void cleanup() {
        // Optional: clear DB after each test
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM BookEntity").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @AfterAll
    public void teardown() {
        emf.close();
    }

    @Test
    public void testPopulateAddsProducts() {
        app.populate();

        EntityManager em = emf.createEntityManager();
        long count = em.createQuery("SELECT COUNT(b) FROM BookEntity b", Long.class).getSingleResult();
        assertTrue(count > 0, "Books should be populated");

        em.close();
    }

    @Test
    public void testAddBook() {
        // Simulate input: "1" for Book
        App.input = new java.util.Scanner(new ByteArrayInputStream("1\n".getBytes()));
        app.addItem();

        EntityManager em = emf.createEntityManager();
        long count = em.createQuery("SELECT COUNT(b) FROM BookEntity b", Long.class).getSingleResult();
        assertEquals(1, count, "Should have 1 book after adding");

        em.close();
    }

    @Test
    public void testDeleteBook() {
        // Populate with some items
        app.populate();

        EntityManager em = emf.createEntityManager();
        // Get one book to delete
        BookEntity book = em.createQuery("SELECT b FROM BookEntity b", BookEntity.class)
                .setMaxResults(1)
                .getSingleResult();
        String bookId = book.getProductId();

        // Simulate user input: choice=1 (Delete Book) then the productId
        String simulatedInput = "1\n" + bookId + "\n";
        App.input = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Delete book
        app.deleteItem();

        // Check if book still exists
        BookEntity deletedBook = null;
        try {
            deletedBook = em.createQuery(
                            "SELECT b FROM BookEntity b WHERE b.productId = :pid", BookEntity.class)
                    .setParameter("pid", bookId)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Expected, book should be deleted
        }

        assertNull(deletedBook, "Book should be deleted from database");
        em.close();
    }

    @Test
    public void testListProductsOutput() {
        app.populate();

        // Simulate input: 7 = List all items
        App.input = new Scanner(new ByteArrayInputStream("7\n".getBytes()));

        // Capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        app.listProducts();

        System.setOut(originalOut);

        String output = outContent.toString();
        assertTrue(output.contains("Found"), "Output should indicate items found");
    }
}
