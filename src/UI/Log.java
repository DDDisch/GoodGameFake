package UI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Log {

    Stage log = new Stage();
    Group root = new Group();
    Scene scene = new Scene(root,250,600);
    static VBox logEvents;

    public Log()
    {
        log.setTitle("Attack Log");
        log.setScene(scene);
        log.setResizable(false);
        log.setX(0);
        log.setY(0);
        log.show();
        logEvents = new VBox();

        root.getChildren().add(logEvents);

    }


    public static void addLogEvent(String message)
    {
        Text t = new Text(message);
        logEvents.getChildren().add(t);
    }


}
