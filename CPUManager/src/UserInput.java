import java.util.Scanner;

public class UserInput {

    private static final Scanner scanner = new Scanner(System.in);

    // Method to get the number of simulation runs from the user
    public static int getNumberOfRuns() {
        System.out.print("Enter the number of simulation runs: ");
        return scanner.nextInt();
    }

    // Method to get the seed value for random number generation from the user
    public static long getSeedValue() {
        System.out.print("Enter the seed value for random number generation: ");
        return scanner.nextLong();
    }
}
