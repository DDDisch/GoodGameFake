package UI;

import PeerToPeer.Client;
import PeerToPeer.Server;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class PreConfig extends VBox {

    Stage startStage = new Stage();

    public PreConfig()
    {
        startStage.setScene(new Scene(this,200,300));
        startStage.show();

        Button client = new Button("Join a host");
        Button host = new Button("Be a Server");
        this.getChildren().addAll(host,client);

        client.setOnAction(event -> {
            Label ipLabel = new Label("IP: ");
            TextField ip = new TextField();

            Label portLabel = new Label("Port: ");
            TextField port = new TextField();
            Button submit = new Button("Submit");

            this.getChildren().removeAll(client,host);
            this.getChildren().addAll(ipLabel,ip, portLabel,port,submit);

            submit.setOnAction(event1 -> {
                try {
                    new Client(ip.getText(),Integer.parseInt(port.getText()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        });

        host.setOnAction(event -> {
            Label portLabel = new Label("Port: ");
            TextField port = new TextField();
            Button submit = new Button("Submit");

            this.getChildren().removeAll(client,host);
            this.getChildren().addAll(portLabel,port,submit);

            submit.setOnAction(event1 -> {
                try {
                    new Server(Integer.parseInt(port.getText()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        });

    }
}
