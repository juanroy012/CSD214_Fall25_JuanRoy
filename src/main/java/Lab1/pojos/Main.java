package Lab1.pojos;

import Lab1.pojos.classes.SaleableItem;

import java.util.HashMap;
import java.util.Map;

import static Lab1.pojos.ui.Menu.mainMenu;

public class Main {

    public static Map<Integer, SaleableItem> itemMap = new HashMap<>();

    public static void main(String[] args) {

        mainMenu();

    }
}
