package com.diegoquintela;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        // creating sockets for connection
        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            Socket socket = serverSocket.accept(); // blocks until client connects to server
            System.out.println("client connected");

            // when client connects, server will use input/output streams to return data
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            //use infinite loop so client can stay connected
            while(true) {
                String echoString = input.readLine();
                if(echoString.equals("exit")) {
                    break;
                }
                output.println("echo from server: " + echoString); //if it doesnt break, echo string back to client
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
