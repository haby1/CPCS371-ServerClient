package ServerClient;

import java.io.*;
import java.net.*;

public class serverSide {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);

        while (true) {
            Socket clientSocket = serverSocket.accept();

            System.out.println("Connected to client: " + clientSocket.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //receive message from client
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); //send message to client, then flush

            while (true) {
                System.out.println("Waiting for character input from client...");
                String character = in.readLine();

                if ("-".equals(character)) {
                    break;
                }

                System.out.println("Received character: " + character);

                System.out.println("Waiting for string input from client...");
                String string = in.readLine();

                System.out.println("Received string: " + string);

                long occurrences = string.chars().filter(c -> c == Character.toLowerCase(character.charAt(0))).count();

                out.println("The number of occurrences are: " + occurrences); //send to client

                System.out.println("Sent occurrences to client");

                System.out.println("Checking for repeat request from client...");
                String repeat = in.readLine();

                if (!"Y".equalsIgnoreCase(repeat)) {
                    break;
                }

            }

            clientSocket.close(); // Close the connection after the loop exits
            System.out.println("Disconnected from client: " + clientSocket.getRemoteSocketAddress());
        }
    }
}