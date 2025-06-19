import java.util.Scanner;

public class ConsoleUI {
    private static Scanner scanner = new Scanner(System.in);

    public String input(String label) {
        System.out.print(label + ">");
        return scanner.nextLine();
    }

    public void show(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.err.println("[ERROR] " + error);
    }
}
