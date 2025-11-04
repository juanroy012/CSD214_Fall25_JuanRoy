package Lab3.pojos;

import Lab3.App;

import java.time.LocalDate;

public abstract class Editable {

    public String getInput() {
        return App.input.nextLine();
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
