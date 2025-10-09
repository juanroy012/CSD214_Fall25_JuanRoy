package Lab2;

import java.util.Scanner;

public class Prompt {

    public static final Scanner input = new Scanner(System.in);
    private static App app = new App();

    public static void exit() {
        System.exit(0);
    }

    public static void print(String prompt){
        System.out.println(prompt);
    }


}
