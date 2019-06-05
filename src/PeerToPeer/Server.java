package PeerToPeer;

import UI.Log;
import javafx.application.Platform;
import sample.Main;

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
    int defPower = 30;


    public Server(int port) throws IOException {

        try {
            servsocket = new ServerSocket(port);
            System.out.println("Waiting for connection...");
            socket = servsocket.accept();
            System.out.println("Verbindung hergestellt: " + socket.toString());
            outSocket = new PrintWriter(socket.getOutputStream(), true);
            inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            Thread readFromServer = new Thread(() -> {
                try {
                    String input;
                    while ((input = inSocket.readLine()) != null) {
                        System.out.println(input);

                        String type = input.substring(0,1);
                        input = input.substring(1);

                        if(type.equals("!")) {
                            int attackPower = Integer.parseInt(input);
                            defPower = Main.sword.calcDefenseAll() + defPower;
                            defPower = Main.spear.calcDefenseAll() + defPower;
                            defPower = Main.bow.calcDefenseAll() + defPower;
                            defPower = Main.crossbow.calcDefenseAll() + defPower;

                            Main.write("-"+(defPower-attackPower));
                            Platform.runLater(()->{
                                Log.addLogEvent("You got attack with: " + (attackPower));
                            });
                        }

                        if (type.equals("-"))
                        {
                            int erg = Integer.parseInt(input);

                            if (erg < 0)
                            {
                                Platform.runLater(()-> {
                                    Log.addLogEvent("Winner");
                                });
                                Main.write("$Lost");
                            }
                            else
                            {
                                Platform.runLater(()->{
                                    Log.addLogEvent("Lost Battle");
                                });
                            }
                        }

                        if(type.equals("$"))
                        {
                            Platform.runLater(()->{
                                Log.addLogEvent("You Lost");
                            });
                        }

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