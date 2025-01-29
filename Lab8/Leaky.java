import java.util.*;

public class Leaky {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Enter the bucket capacity: ");
        int bucketCapacity = in.nextInt();

        System.out.print("Enter the bucket rate (Rate at which packets are sent): ");
        int bucketRate = in.nextInt();

        System.out.print("Enter the number of packets to be sent: ");
        int n = in.nextInt();

        int[] packetSizes = new int[n]; // Store all packet sizes first

        System.out.println("Enter the packet sizes one by one:");
        for (int i = 0; i < n; i++) {
            packetSizes[i] = in.nextInt();
        }

        System.out.printf("%-10s %-10s %-10s %-10s %-10s%n", "Time_t", "P_size", "Accepted", "Sent", "Remaining");

        int bucketRemaining = 0;
        
        for (int i = 0; i < n; i++) {
            int received;
            
            // Check if the packet can be accepted
            if (bucketRemaining + packetSizes[i] > bucketCapacity) {
                received = -1; // Packet is dropped
            } else {
                received = packetSizes[i];
                bucketRemaining += packetSizes[i];
            }

            // Send packets according to the bucket rate
            int sent = Math.min(bucketRemaining, bucketRate);
            bucketRemaining -= sent;

            // Print result
            if (received == -1) {
                System.out.printf("%-10d %-10d %-10s %-10d %-10d%n", i + 1, packetSizes[i], "Dropped", sent, bucketRemaining);
            } else {
                System.out.printf("%-10d %-10d %-10d %-10d %-10d%n", i + 1, packetSizes[i], received, sent, bucketRemaining);
            }
        }

        in.close();
    }
}
