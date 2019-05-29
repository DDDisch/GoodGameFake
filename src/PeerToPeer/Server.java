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
    Socket socket;
    ServerSocket servsocket;
    static PrintWriter outSocket = null;
    static BufferedReader inSocket = null;
    static BufferedReader userInputReader;
    Boolean connection = false;

    public Server(int port) throws IOException {

        try {
            servsocket = new ServerSocket(port);
            System.out.println("Waiting for connection...");
            socket = servsocket.accept();
            System.out.println("Verbindung hergestellt: " + socket.toString());
            connection = true;
            outSocket = new PrintWriter(socket.getOutputStream(), true);
            inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            Thread readFromServer = new Thread(()->{
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


            if (!readFromServer.isAlive())
            {
                connection = false;
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