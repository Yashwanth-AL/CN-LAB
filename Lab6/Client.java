// package Lab6;

import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("127.0.0.1");

        System.out.println("Enter messages to send (type 'exit' to stop):");
        while (true) {
            String message = scanner.nextLine();
            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4000);
            socket.send(packet);

            if (message.equalsIgnoreCase("exit")) {
                socket.close();
                break;
            }
        }
        scanner.close();
    }
}

