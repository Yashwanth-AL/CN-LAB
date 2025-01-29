import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //number of packets to be sent
        System.out.println("Enter the maximum number of packets:");
        int maxPackets = scanner.nextInt();

        //size of the queue the packets can be stored
        System.out.println("Enter the queue size:");
        int queueSize = scanner.nextInt();

        System.out.println("Enter the maximum probability:");
        double maxProbability = scanner.nextDouble();

        //used to calculated the drop probabilty (max-min)
        System.out.println("Enter the minimum probability:");
        double minProbability = scanner.nextDouble();

        //the value after which the congestion control comes to action 
        System.out.println("Enter the threshold value:");
        int threshold = scanner.nextInt();

        simulateCongestion(maxPackets, queueSize, maxProbability, minProbability, threshold);
        scanner.close();
    }

    private static void simulateCongestion(int maxPackets, int queueSize, double maxProbability, double minProbability, int threshold) {
        Random rand = new Random(System.currentTimeMillis());
        int queueLength = 0;

        for (int i = 0; i < maxPackets; i++) {
            double dropProbability = calculateDropProbability(queueLength, queueSize, maxProbability, minProbability, threshold);

            if (queueLength >= threshold && rand.nextDouble() < dropProbability) {
                System.out.println("Packet dropped (CONGESTION AVOIDANCE)");
                //checking the threshold value and the probabilty to check whether to accept or reject the packet
            } else {
                System.out.println("Packet accepted " + (i + 1));
                queueLength++;
            }
        }
    }

    private static double calculateDropProbability(int currentQueueLength, int queueSize, double maxProbability, double minProbability, int threshold) {
        double slope = (maxProbability - minProbability) / (queueSize - threshold);
        return minProbability + slope * (currentQueueLength - threshold);
    }
    
}
