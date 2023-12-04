package ServerClient;

import java.io.*;
import java.net.*;

public class clientSide {

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", 12345); //localhost = 127.0.0.1

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // read input from client
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // send messages from client to server
             BufferedReader serverIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {// reads output from server
            String character;
            String string;
            String repeat;

            while (true) {
                // Loop until a single character is entered
                do {
                    System.out.println("Enter a Character to be searched");
                    character = in.readLine();
                    if(character.length()!=1){
                        System.out.println("**You've entered more than one character!**");
                    }
                } while (character.length() != 1);

                System.out.println("Enter a String: ");
                string = in.readLine();

                // Send the character to be searched
                out.println(character);

                // Send the string to search in
                out.println(string);

                // Receive the number of occurrences from the server
                String occurrences = serverIn.readLine();

                // Display the number of occurrences
                System.out.println(occurrences);

                System.out.println("Want to repeat (Y/N): ");
                repeat = in.readLine();

                // Send the repeat request to the server
                out.println(repeat); // This is the added line

                // Terminate the program if the user enters "N"
                if ("N".equalsIgnoreCase(repeat)) {
                    break;
                }
            }
        }

        clientSocket.close();
        System.out.println("Thank You!");
    }
}