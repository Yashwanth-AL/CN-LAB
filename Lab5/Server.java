// package Lab5;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(4000)) {
            System.out.println("Server is waiting for client...");

            try (Socket sock = serverSocket.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                 PrintWriter writer = new PrintWriter(sock.getOutputStream(), true)) {

                System.out.println("Client connected. Waiting for filename...");
                String fname = reader.readLine();  // Receive filename from client

                try (BufferedReader fileReader = new BufferedReader(new FileReader(fname))) {
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        writer.println(line);
                    }
                } catch (IOException e) {
                    writer.println("File not found.");
                }

                System.out.println("Closing connection...");
            }
        }
    }
}
