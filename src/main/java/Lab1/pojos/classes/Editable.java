package Lab1.pojos.classes;

import java.util.Scanner;

public abstract class Editable<T> {
    public Scanner input = new Scanner(System.in);
    T userInput;

    public Scanner getInput() {
        return input;
    }

    public String  getInput(T userInput) {
        this.userInput = userInput;
        return String.valueOf(userInput);
    }

    public abstract void edit();

    public abstract void initialize();
}
