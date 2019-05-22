package PeerToPeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    static Socket socket = null;
    static PrintWriter outSocket = null;
    static BufferedReader inSocket = null;
    static BufferedReader userInputReader;

    public Client(String host, int port) throws IOException {

        System.out.println("Trying to connect...");
        socket = new Socket(host, port);
        System.out.println("Connected");
        outSocket = new PrintWriter(socket.getOutputStream(), true);
        inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Thread writeToServer = new Thread(()->{
        try {
            userInputReader = new BufferedReader(new InputStreamReader(System.in));
            String userInput;

            while ((userInput = userInputReader.readLine()) != null) {
                outSocket.println(userInput);
            }

        } catch (UnknownHostException e) {
            System.exit(1);
        } catch (IOException e) {
            System.exit(1);
        }
        });
        writeToServer.start();

        Thread readFromServer = new Thread(()->{
           try {
               String input;
               while ((input = inSocket.readLine()) != null) {
                   System.out.println(input);
               }
           } catch (UnknownHostException e) {
               System.exit(1);
           } catch (IOException e) {
               System.exit(1);
           }
        });
        readFromServer.start();

        if (!writeToServer.isAlive() || !readFromServer.isAlive())
        {
            outSocket.close();
            inSocket.close();
            userInputReader.close();
            socket.close();
        }
    } }