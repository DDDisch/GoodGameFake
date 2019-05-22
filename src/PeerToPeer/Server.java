package PeerToPeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server
{
    static Socket socket = null;
    static PrintWriter outSocket = null;
    static BufferedReader inSocket = null;
    static BufferedReader userInputReader;

    public Server(int port) throws IOException {

        try {
            ServerSocket servsocket = new ServerSocket(port);
            System.out.println("Waiting for connection...");
            Socket socket = servsocket.accept();
            System.out.println("Verbindung hergestellt: " + socket.toString());
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
                System.out.println("Verbindung beenden");
                outSocket.close();
                inSocket.close();
                userInputReader.close();
                socket.close();
                servsocket.close();
                socket.close();
            }
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(1);
        } }
}