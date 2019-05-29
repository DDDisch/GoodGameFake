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
    Boolean connection = false;

    public Client(String host, int port) throws IOException {

        System.out.println("Trying to connect...");
        socket = new Socket(host, port);
        System.out.println("Connected");
        connection = true;
        outSocket = new PrintWriter(socket.getOutputStream(), true);
        inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Thread readFromServer = new Thread(() -> {
            try {
                String input;
                while ((input = inSocket.readLine()) != null) {
                    System.out.println(input);


                    //Calculation or Registration of Attack



                }
            } catch (UnknownHostException e) {
                System.exit(1);
            } catch (IOException e) {
                System.exit(1);
            }
        });
        readFromServer.start();

        if (!readFromServer.isAlive()) {
            connection = false;
            outSocket.close();
            inSocket.close();
            userInputReader.close();
            socket.close();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void write(String userInput)
    {
        Thread writeToServer = new Thread(() -> {
            outSocket.println(userInput);
        });
        writeToServer.start();
    }


}