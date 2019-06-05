package PeerToPeer;

import sample.Main;

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

        Thread readFromServer = new Thread(() -> {
            try {
                String input;
                while ((input = inSocket.readLine()) != null) {
                    System.out.println(input);

                    String type = input.substring(0,1);
                    input = input.substring(1);
                    if(type.equals("!")) {
                        int attackPower = Integer.parseInt(input);
                        int defPower = 30;
                        defPower = Main.sword.calcDefenseAll() + defPower;
                        defPower = Main.spear.calcDefenseAll() + defPower;
                        defPower = Main.bow.calcDefenseAll() + defPower;
                        defPower = Main.crossbow.calcDefenseAll() + defPower;

                        Main.write("-"+(defPower-attackPower));
                    }

                    if (type.equals("-"))
                    {
                            int erg = Integer.parseInt(input);

                            if (erg < 0)
                            {
                                System.out.println("Winner");
                            }
                            else
                            {
                                System.out.println("Lost Battle");
                            }
                    }

                }
            } catch (UnknownHostException e) {
                System.exit(1);
            } catch (IOException e) {
                System.exit(1);
            }
        });
        readFromServer.start();

        if (!readFromServer.isAlive()) {
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