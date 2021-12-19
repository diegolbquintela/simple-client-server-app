package com.diegoquintela;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // if localhost doesnt work, pass 127.0.0.1 for working locally
        // port must be the same as client
        try (Socket socket = new Socket("localhost", 5050)) {
            BufferedReader echoes = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            // do while because we want it to execute at least once
            do {
                System.out.println("enter string to be echoed: ");
                echoString = scanner.nextLine();

                stringToEcho.println(echoString);
                if(!echoString.equals("exit")) {
                    response = echoes.readLine();
                    System.out.println(response);
                }
            } while(!echoString.equals("exit"));

        } catch (IOException e) {
            System.out.println("client error: " + e.getMessage());
        }


    }
}
