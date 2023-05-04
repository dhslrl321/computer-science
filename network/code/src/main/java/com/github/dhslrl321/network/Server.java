package com.github.dhslrl321.network;

import java.io.*;
import java.net.*;

import static com.github.dhslrl321.network.Constants.PORT;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Server listening on port " + PORT + "...");

            // Get input/output streams for sending/receiving data
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            // Receive SYN packet from the client
            String request = in.readUTF();
            if (request.equals("SYN")) {
                System.out.println("Received SYN packet from client.");

                // Send SYN-ACK packet to the client
                out.writeUTF("SYN-ACK");
                System.out.println("Sent SYN-ACK packet to client.");

                // Receive ACK packet from the client
                String response = in.readUTF();
                if (response.equals("ACK")) {
                    System.out.println("Received ACK packet from client.");
                    // Close the connection
                    clientSocket.close();
                    System.out.println("Connection closed.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}