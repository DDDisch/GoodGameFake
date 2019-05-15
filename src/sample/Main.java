package sample;

import UI.MenuRight;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    MenuRight menuRight;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        menuRight = new MenuRight(primaryStage);
        primaryStage.setTitle("Strategy Game");
        primaryStage.setScene(new Scene(root, 300, 500));
        primaryStage.setFullScreen(true);
        primaryStage.show();

        menuRight.createMenuItem("Build", 1);
        menuRight.createMenuItem("Soldier", 2 );
        menuRight.createMenuItem("Attack",3 );

        root.setRight(menuRight.getVbox());

    }

    public static void main(String[] args) {
        launch(args);
    }
}
