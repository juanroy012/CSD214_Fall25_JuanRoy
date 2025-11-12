package Lab3;

import Lab3.entities.*;
import Lab3.pojos.*;
import Lab3.repositories.*;
import Lab3.services.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest {

    // These streams will hold the original System streams
    private static final InputStream originalIn = System.in;
    private static final PrintStream originalOut = System.out;

    // This stream will be used to capture output for tests
    private ByteArrayOutputStream testOut;
    private App appInMemory;
    private App appMySql;
    private App appH2;
    private App app;
    private ByteArrayOutputStream outContent;

    @BeforeAll
    public void setup() {
        outContent = new ByteArrayOutputStream();
        appInMemory = new App(new ByteArrayInputStream("".getBytes()), new PrintStream(outContent), new ProductService(new InMemoryProductRepository()));
        appMySql = new App(new ByteArrayInputStream("".getBytes()), new PrintStream(outContent), new ProductService(new MySQLProductRepository("default-test")));
        appH2 = new App(new ByteArrayInputStream("".getBytes()), new PrintStream(outContent), new ProductService(new H2ProductRepository("h2-test")));
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private App getAppWithSimulatedInput(String input, ProductService productService) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        return new App(testIn, System.out, productService); // System.out is already redirected to testOut
    }

    @Test
    public void testPopulateAddsProducts() {
        appInMemory.populate();
        appMySql.populate();
        appH2.populate();

        List<ProductEntity> inMemoryOutput = appInMemory.productService.getAllProducts();
        List<ProductEntity> MySqlOutput = appMySql.productService.getAllProducts();
        List<ProductEntity> H2Output = appH2.productService.getAllProducts();
        assertTrue(inMemoryOutput.size() > 10, "There should be more than one item in the list");
        assertTrue(MySqlOutput.size() > 10, "There should be more than one item in the list");
        assertTrue(H2Output.size() > 10, "There should be more than one item in the list");
    }

    @Test
    public void testAddingItemsInMemory() {
        String expectedDescription = "Testing ticket";
        double expectedPrice = 99.99;

        String userInput = "1\n" +
                "5\n" +
                expectedDescription + "\n" +
                expectedPrice + "\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput, new ProductService(new InMemoryProductRepository()));
        app.run();

        List<ProductEntity> items = app.productService.getAllProducts();
        ProductEntity addedItem = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription))
                .findFirst()
                .orElse(null);

        assertNotNull(addedItem, "Added item should be in the list");
    }

    @Test
    public void testAddingItemsMySql() {
        String expectedDescription = "Testing ticket";
        double expectedPrice = 99.99;

        String userInput = "1\n" +
                "5\n" +
                expectedDescription + "\n" +
                expectedPrice + "\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput, new ProductService(new MySQLProductRepository("default-test")));
        app.run();

        List<ProductEntity> items = app.productService.getAllProducts();
        ProductEntity addedItem = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription))
                .findFirst()
                .orElse(null);

        assertNotNull(addedItem, "Added item should be in the list");
    }

    @Test
    public void testAddingItemsH2() {
        String expectedDescription = "Testing ticket";
        double expectedPrice = 99.99;

        String userInput = "1\n" +
                "5\n" +
                expectedDescription + "\n" +
                expectedPrice + "\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput, new ProductService(new H2ProductRepository("h2-test")));
        app.run();

        List<ProductEntity> items = app.productService.getAllProducts();
        ProductEntity addedItem = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription))
                .findFirst()
                .orElse(null);

        assertNotNull(addedItem, "Added item should be in the list");
    }

    @Test
    public void testDeletingItemsInMemory() {
        String expectedDescription1 = "Testing ticket 1";
        String expectedDescription2 = "Testing ticket 2";
        double expectedPrice = 99.99;

        String userInput = "1\n" +
                "5\n" +
                expectedDescription1 + "\n" +
                expectedPrice + "\n" +
                "1\n" +
                "5\n" +
                expectedDescription2 + "\n" +
                expectedPrice + "\n" +
                "3\n" +
                "1\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput, new ProductService(new InMemoryProductRepository()));
        app.run();

        List<ProductEntity> items = app.productService.getAllProducts();
        ProductEntity addedItem1 = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription1))
                .findFirst()
                .orElse(null);
        ProductEntity addedItem2 = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription2))
                .findFirst()
                .orElse(null);

        assertTrue(items.size() == 1, "Item list should have 1 added product");
        assertNull(addedItem1, "Item 1 should've been deleted");
        assertNotNull(addedItem2, "Added item should be in the list");
    }

    @Test
    public void testDeletingItemsMySql() {
        String expectedDescription1 = "Testing ticket 1";
        String expectedDescription2 = "Testing ticket 2";
        double expectedPrice = 99.99;

        String userInput = "1\n" +
                "5\n" +
                expectedDescription1 + "\n" +
                expectedPrice + "\n" +
                "1\n" +
                "5\n" +
                expectedDescription2 + "\n" +
                expectedPrice + "\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput, new ProductService(new MySQLProductRepository("default-test")));
        app.run();

        List<ProductEntity> itemsBefore = app.productService.getAllProducts();
        ProductEntity itemToDelete = itemsBefore.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription1))
                .findFirst()
                .orElse(null);

        assertNotNull(itemToDelete);
        String userInput2 = "3\n" + itemToDelete.getId() + "\n" + "99\n";

        app = getAppWithSimulatedInput(userInput2, new ProductService(new MySQLProductRepository("default-test")));
        app.run();

        List<ProductEntity> items = app.productService.getAllProducts();
        ProductEntity addedItem1 = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription1))
                .findFirst()
                .orElse(null);
        ProductEntity addedItem2 = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription2))
                .findFirst()
                .orElse(null);


        assertTrue(items.size() == itemsBefore.size() - 1, "Item list should have been decremented by 1");
        assertNull(addedItem1, "Item 1 should've been deleted");
        assertNotNull(addedItem2, "Added item should be in the list");
    }

    @Test
    public void testDeletingItemsH2() {
        String expectedDescription1 = "Testing ticket 1";
        String expectedDescription2 = "Testing ticket 2";
        double expectedPrice = 99.99;

        String userInput = "1\n" +
                "5\n" +
                expectedDescription1 + "\n" +
                expectedPrice + "\n" +
                "1\n" +
                "5\n" +
                expectedDescription2 + "\n" +
                expectedPrice + "\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput, new ProductService(new H2ProductRepository("h2-test")));
        app.run();

        List<ProductEntity> itemsBefore = app.productService.getAllProducts();
        ProductEntity itemToDelete = itemsBefore.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription1))
                .findFirst()
                .orElse(null);

        assertNotNull(itemToDelete);
        String userInput2 = "3\n" + itemToDelete.getId() + "\n" + "99\n";

        app = getAppWithSimulatedInput(userInput2, new ProductService(new H2ProductRepository("h2-test")));
        app.run();

        List<ProductEntity> items = app.productService.getAllProducts();
        ProductEntity addedItem1 = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription1))
                .findFirst()
                .orElse(null);
        ProductEntity addedItem2 = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription2))
                .findFirst()
                .orElse(null);


        assertTrue(items.size() == itemsBefore.size() - 1, "Item list should have been decremented by 1");
        assertNull(addedItem1, "Item 1 should've been deleted");
        assertNotNull(addedItem2, "Added item should be in the list");
    }

    @Test
    public void testEditingItemsInMemory() {
        String initialDescription = "Testing ticket";
        double expectedPrice = 99.99;

        String expectedDescription = "Edited Ticket";

        String userInput = "1\n" +
                "5\n" +
                initialDescription + "\n" +
                expectedPrice + "\n" +
                "2\n" +
                "1\n" +
                expectedDescription + "\n" +
                expectedPrice + "\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput, new ProductService(new InMemoryProductRepository()));
        app.run();

        List<ProductEntity> items = app.productService.getAllProducts();
        ProductEntity initialItem = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(initialDescription))
                .findFirst()
                .orElse(null);

        ProductEntity editedItem = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription))
                .findFirst()
                .orElse(null);

        assertNull(initialItem, "Item should've already been edited");
        assertNotNull(editedItem, "Added item should be in the list");
    }

    @Test
    public void testEditingItemsMySql() {
        String initialDescription = "Initial ticket";
        double expectedPrice = 99.99;

        String expectedDescription = "Edited Ticket";

        String userInput = "1\n" +
                "5\n" +
                initialDescription + "\n" +
                expectedPrice + "\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput, new ProductService(new MySQLProductRepository("default-test")));
        app.run();

        List<ProductEntity> itemsBefore = app.productService.getAllProducts();
        ProductEntity itemToEdit = itemsBefore.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(initialDescription))
                .findFirst()
                .orElse(null);

        assertNotNull(itemToEdit);
        String userInput2 = "2\n" +
                itemToEdit.getId() + "\n" +
                expectedDescription + "\n" +
                expectedPrice + "\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput2, new ProductService(new MySQLProductRepository("default-test")));
        app.run();

        List<ProductEntity> items = app.productService.getAllProducts();
        ProductEntity initialItem = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(initialDescription))
                .findFirst()
                .orElse(null);
        ProductEntity editedItem = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription))
                .findFirst()
                .orElse(null);

        assertNull(initialItem, "Item should've already been edited");
        assertNotNull(editedItem, "Added item should be in the list");
    }

    @Test
    public void testEditingItemsH2() {
        String initialDescription = "Initial ticket";
        double expectedPrice = 99.99;

        String expectedDescription = "Edited Ticket";

        String userInput = "1\n" +
                "5\n" +
                initialDescription + "\n" +
                expectedPrice + "\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput, new ProductService(new H2ProductRepository("default-test")));
        app.run();

        List<ProductEntity> itemsBefore = app.productService.getAllProducts();
        ProductEntity itemToEdit = itemsBefore.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(initialDescription))
                .findFirst()
                .orElse(null);

        assertNotNull(itemToEdit);
        String userInput2 = "2\n" +
                itemToEdit.getId() + "\n" +
                expectedDescription + "\n" +
                expectedPrice + "\n" +
                "99\n";

        app = getAppWithSimulatedInput(userInput2, new ProductService(new H2ProductRepository("default-test")));
        app.run();

        List<ProductEntity> items = app.productService.getAllProducts();
        ProductEntity initialItem = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(initialDescription))
                .findFirst()
                .orElse(null);
        ProductEntity editedItem = items.stream()
                .filter(item -> item instanceof TicketEntity)
                .filter(item -> ((TicketEntity) item).getDescription().equals(expectedDescription))
                .findFirst()
                .orElse(null);

        assertNull(initialItem, "Item should've already been edited");
        assertNotNull(editedItem, "Added item should be in the list");
    }
}
