package UI;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinLosStage {
    public static void createStage(Boolean win) {
        Text text;
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 800, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ending");
        stage.setResizable(false);
        stage.setX(100);
        stage.setY(100);
        Button cancel = new Button("Quit");

        if(win) {
            text = new Text("You have won the Battle of Doom!");
        } else {
            text = new Text("You have lost the Battle of Doom!");
        }

        text.setFont(new Font(40));

        borderPane.setCenter(new VBox(text, cancel));

        stage.show();

        cancel.setOnAction(e -> System.exit(0));
    }
}
