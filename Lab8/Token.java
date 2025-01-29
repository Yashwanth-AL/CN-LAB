import java.util.Scanner;

public class Token {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User inputs
        System.out.print("Enter the bucket capacity: ");
        int bucketCapacity = scanner.nextInt();

        System.out.print("Enter the token generation rate: ");
        int tokenGenRate = scanner.nextInt();

        System.out.print("Enter the number of cycles: ");
        int cycles = scanner.nextInt();

        int tokenRemaining = 0; // Tokens currently in the bucket

        // Header for output table
        System.out.printf("%-10s %-18s %-15s %-20s%n", "Time_t", "Tokens Requested", "Tokens Sent", "Tokens Remaining");

        // Processing tokens in each cycle
        for (int i = 1; i <= cycles; i++) {
            int tokensRequested = tokenGenRate; // Tokens generated per cycle
            int tokensSent;

            // If adding tokens exceeds bucket capacity, adjust accordingly
            if (tokenRemaining + tokensRequested > bucketCapacity) {
                tokensSent = bucketCapacity - tokenRemaining; // Add only up to capacity
                tokenRemaining = bucketCapacity; // Bucket is full
            } else {
                tokensSent = tokensRequested; // Accept all generated tokens
                tokenRemaining += tokensRequested;
            }

            // Print formatted output for each cycle
            System.out.printf("%-10d %-18d %-15d %-20d%n", i, tokensRequested, tokensSent, tokenRemaining);
        }

        scanner.close();
    }
}
