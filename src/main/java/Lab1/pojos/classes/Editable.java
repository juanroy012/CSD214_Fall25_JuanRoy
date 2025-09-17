package Lab1.pojos.classes;

import java.util.Scanner;
import java.util.Date;

public abstract class Editable {
    private final Scanner input = new Scanner(System.in);

    public Scanner getInput() {
        return input;
    }

    public abstract void edit();

    public abstract void initialize();
}
