package Lab2;

import Lab2.pojos.SaleableItem;
import Lab2.pojos.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    // These streams will hold the original System streams
    private static final InputStream originalIn = System.in;
    private static final PrintStream originalOut = System.out;

    // This stream will be used to capture output for tests
    private ByteArrayOutputStream testOut;

    // @BeforeAll runs ONCE before any tests in this class start.
    // It MUST be static.
    @BeforeAll
    public static void setupAll() {
        System.out.println("==========================================");
        System.out.println("  Starting AppMenu Tests...");
        System.out.println("==========================================");
    }

    // @AfterAll runs ONCE after all tests in this class have finished.
    // It MUST be static.
    @AfterAll
    public static void tearDownAll() {
        // Restore the original System.out so we can see the final message
        System.setOut(originalOut);
        System.out.println("==========================================");
        System.out.println("  AppMenu Tests Finished.");
        System.out.println("==========================================");
    }


    // @BeforeEach runs before EACH @Test method.
    @BeforeEach
    public void setUpStreams() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    // @AfterEach runs after EACH @Test method.
    @AfterEach
    public void restoreStreams() {
        System.setIn(originalIn);
        // We don't restore System.out here anymore, as tearDownAll needs to be the last one to use it.
        // It's generally safe to let it be reset by the next setUpStreams or by tearDownAll.
        // For clarity, we'll restore it fully in tearDownAll.
    }

    /**
     * Helper method to create an App instance with simulated input.
     * @param input The string representing user input, with newlines for each entry.
     * @return A configured App instance ready for testing.
     */
    private App getAppWithSimulatedInput(String input) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        return new App(testIn, System.out); // System.out is already redirected to testOut
    }

    @Test
    void testMainMenuQuitOption() {
        String userInput = "99\n";
        App app = getAppWithSimulatedInput(userInput);
        app.run();
        String output = testOut.toString();
        assertTrue(output.contains("***********************"));
        assertTrue(output.contains("99. Quit"));
        assertTrue(output.contains("Exiting application. Goodbye!"));
    }



    @Test
    void testAddTicketSuccessfully() {
        // ARRANGE: Define the complete user interaction flow as a string.
        // 1. Select '1' from the main menu to "Add Items".
        // 2. Select '4' from the add menu to "Add Ticket".
        // 3. Enter the ticket description.
        // 4. Enter the ticket price.
        // 5. Select '99' to exit the "Add Item" sub-menu.
        // 6. Select '99' to exit the application and end the test.
        final String expectedDescription = "Evening Gala Charity Event";
        final double expectedPrice = 150.75;

        String userInput = "1\n" +       // Main menu: Add Items
                "4\n" +       // Add Item menu: Add Ticket
                expectedDescription + "\n" +
                expectedPrice + "\n" +
                "99\n" +      // Exit Add Item menu
                "99\n";      // Exit Application

        App app = getAppWithSimulatedInput(userInput);

        // ACT: Run the application logic with the simulated input.
        app.populate();
        app.run();

        // ASSERT: Verify both the output and the final state of the application.

        // 1. Assert the output: Check if the user was prompted correctly.
        String output = testOut.toString();
//        assertTrue(output.contains("4. Add Ticket"), "The 'Add Ticket' option should be displayed.");
//        assertTrue(output.contains("Enter ticket text"), "The user should be prompted for the ticket description.");
//        assertTrue(output.contains("Edit ticket price"), "The user should be prompted for the ticket price.");

        // 2. Assert the state: Check the internal list of items.
        List<SaleableItem> items = app.getSaleableItems();

        // The populate() method adds 8 items. This test should add one more.
        assertEquals(9, items.size(), "There should be 9 items in total (8 from populate + 1 new ticket).");

        // Find the newly added ticket in the list. This is more robust than just getting the last item.
        SaleableItem addedItem = items.stream()
                .filter(item -> item instanceof Ticket)
                .filter(item -> ((Ticket) item).getDescription().equals(expectedDescription))
                .findFirst()
                .orElse(null);

        // Verify that our ticket was actually found.
        assertNotNull(addedItem, "The newly created ticket should be found in the saleable items list.");

        // Verify the properties of the found ticket.
        assertTrue(addedItem instanceof Ticket, "The added item must be an instance of Ticket.");
        Ticket addedTicket = (Ticket) addedItem; // Cast for specific assertions
        assertEquals(expectedDescription, addedTicket.getDescription(), "The ticket description should match the input.");
        assertEquals(expectedPrice, addedTicket.getPrice(), "The ticket price should match the input.");
    }

    @Test
    void testEditTicket() {
        final String expectedDescription = "Morning Gala Charity Event";
        final double expectedPrice = 175.75;

        App app = new App();
        app.populate();

        // Find a Ticket ID
        Long ticketId = app.itemMap.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Ticket)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No Ticket found"));

        // Test editing the ticket
        String editInput = "2\n" +
                ticketId + "\n" +
                expectedDescription + "\n" +
                expectedPrice + "\n" +
                "99\n";

        App app2 = getAppWithSimulatedInput(editInput);
        app2.itemMap.putAll(app.itemMap);
        app2.run();

        List<SaleableItem> items = app.getSaleableItems();

        SaleableItem addedItem = items.stream()
                .filter(item -> item instanceof Ticket)
                .filter(item -> ((Ticket) item).getDescription().equals(expectedDescription))
                .findFirst()
                .orElse(null);

        // Verify the properties of the found ticket.
        assertInstanceOf(Ticket.class, addedItem, "The added item must be an instance of Ticket.");
        Ticket addedTicket = (Ticket) addedItem; // Cast for specific assertions
        assertEquals(expectedDescription, addedTicket.getDescription(), "The ticket description should match the input.");
        assertEquals(expectedPrice, addedTicket.getPrice(), "The ticket price should match the input.");
    }

    @Test
    void testDeleteTicket() {
        App app = new App();
        app.populate();

        // Find a Ticket ID
        Long ticketId = app.itemMap.entrySet().stream()
                .filter(entry -> entry.getValue() instanceof Ticket)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No Ticket found"));

        int initialSize = app.itemMap.size();

        // Delete the ticket
        String deleteInput = "3\n" + ticketId + "\n" + "99\n";

        App app2 = getAppWithSimulatedInput(deleteInput);
        app2.itemMap.putAll(app.itemMap);
        app2.run();

        assertEquals(initialSize - 1, app2.itemMap.size(), "The map should be 7 (8 from populate - 1 from deletion)");
        assertFalse(app2.itemMap.containsKey(ticketId), "The map should not contain the deleted ticket ID");
    }
}