package Lab1.pojos.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        return Boolean.parseBoolean(getInput());
    }
    public Date getInput(Date userInput) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false);

        while (true) {
            System.out.println("Enter the issue date (yyyy-mm-dd): ");
            String dateString = getInput();
            try {
                return simpleDateFormat.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Input valid date!");
            }
        }
    }

    public Enum getInput(Enum userInput) {
        return userInput;
    }

    public abstract void edit();

    public abstract void initialize();
}
