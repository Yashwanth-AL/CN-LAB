import java.util.Scanner;

public class Main {

    // Function to perform division (XOR operation)
    private static int[] divide(int[] dividend, int[] divisor) {
        int n = dividend.length, m = divisor.length;
        int[] remainder = dividend.clone();

        for (int i = 0; i <= n - m; i++) {
            if (remainder[i] == 1) { // Only perform XOR if the leading bit is 1
                for (int j = 0; j < m; j++)
                    remainder[i + j] ^= divisor[j]; // XOR operation
            }
        }
        return remainder;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("At Sender Side:");
        System.out.print("Enter message bits: ");
        String message = sc.nextLine();
        System.out.print("Enter generator: ");
        String generator = sc.nextLine();

        int messageLength = message.length();
        int generatorLength = generator.length();

        int[] data = new int[messageLength + generatorLength - 1];
        int[] divisor = new int[generatorLength];

        // Convert input strings to integer arrays
        for (int i = 0; i < messageLength; i++)
            data[i] = message.charAt(i) - '0';

        for (int i = 0; i < generatorLength; i++)
            divisor[i] = generator.charAt(i) - '0';

        // Perform division to calculate CRC
        int[] remainder = divide(data, divisor);

        // Append remainder to message
        for (int i = 0; i < generatorLength - 1; i++)
            data[messageLength + i] = remainder[messageLength + i];

        // Display the transmitted message (data + checksum)
        System.out.print("Transmitted codeword: ");
        for (int bit : data)
            System.out.print(bit);
        System.out.println();

        System.out.println("\nAt Receiver Side:");
        System.out.print("Enter received codeword: ");
        String receivedMessage = sc.nextLine();

        int[] receivedData = new int[receivedMessage.length()];
        for (int i = 0; i < receivedMessage.length(); i++)
            receivedData[i] = receivedMessage.charAt(i) - '0';

        // Perform division to check for errors
        int[] receivedRemainder = divide(receivedData, divisor);

        // Check if remainder is all zeros
        boolean valid = true;
        for (int i = messageLength; i < receivedRemainder.length; i++) {
            if (receivedRemainder[i] == 1) {
                valid = false;
                break;
            }
        }

        if (valid)
            System.out.println("Data stream is valid (No CRC error).");
        else
            System.out.println("Data stream is invalid. CRC error detected.");

        sc.close();
    }
}
