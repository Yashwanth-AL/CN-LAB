// package Lab5;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        try (Socket sock = new Socket("127.0.0.1", 4000);
             Scanner sc = new Scanner(System.in);
             PrintWriter pWrite = new PrintWriter(sock.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

            System.out.print("Enter Filename: ");
            pWrite.println(sc.nextLine());  // Send filename to server

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
