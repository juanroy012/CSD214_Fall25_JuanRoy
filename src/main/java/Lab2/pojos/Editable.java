package Lab2.pojos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public abstract class Editable {
    public Scanner input = new Scanner(System.in);

    public String getInput() {
        return input.nextLine();
    }

    public String getInput(String userInput) {
        return getInput();
    }
    public int getInput(int userInput) {
        return Integer.parseInt(getInput());
    }
    public double getInput(double userInput) {
        return Double.parseDouble(getInput());
    }
    public boolean getInput(boolean userInput) {
        String input1 = getInput().toLowerCase();
        return switch (input1) {
            case "yes", "y", "true" -> true;
            case "no", "n", "false" -> false;
            default -> throw new IllegalStateException("Unexpected value: " + input1);
        };
    }

    public LocalDate getInput(LocalDate userInput) {
        String dateString = getInput();
        return LocalDate.parse(dateString);

    }

    public Enum getInput(Enum userInput) {
        return userInput;
    }

    public abstract void edit();

    public abstract void initialize();
}
