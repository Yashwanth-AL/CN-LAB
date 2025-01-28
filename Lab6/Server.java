// package Lab6;

import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(4000);
        byte[] buffer = new byte[65535];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        System.out.println("Waiting for messages...");

        while (true) {
            socket.receive(packet);
            String message = new String(packet.getData(), 0, packet.getLength()).trim();
            System.out.println("Received: " + message);

            if (message.equalsIgnoreCase("exit")) {
                socket.close();
                break;
            }
        }
    }
}
