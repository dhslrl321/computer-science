package com.github.dhslrl321.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Server IP address
        int serverPort = 8080; // Server port number

        try {
            // Establish connection with the server
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to server.");

            // Get input/output streams for sending/receiving data
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // Send SYN packet to the server
            out.writeUTF("SYN");
            System.out.println("Sent SYN packet to server.");

            // Receive SYN-ACK packet from the server
            String response = in.readUTF();
            if (response.equals("SYN-ACK")) {
                System.out.println("Received SYN-ACK packet from server.");

                // Send ACK packet to the server
                out.writeUTF("ACK");
                System.out.println("Sent ACK packet to server.");

                // Communication with the server
                // ...

                // Close the connection
                socket.close();
                System.out.println("Connection closed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
