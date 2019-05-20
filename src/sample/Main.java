package sample;

import UI.MenuRight;
import UI.StatusBar;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    MenuRight menuRight;
    StatusBar statusBar = new StatusBar();
    private SimpleIntegerProperty wood = new SimpleIntegerProperty(300);
    private SimpleIntegerProperty stone = new SimpleIntegerProperty(300);
    private SimpleIntegerProperty food = new SimpleIntegerProperty(300);
    private SimpleIntegerProperty money = new SimpleIntegerProperty(300);

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

        addListener();

        root.setRight(menuRight.getVbox());
        root.setTop(statusBar.getHbox());

    }

    private void addListener() {
        wood.addListener((observable, oldValue, newValue) -> {
            statusBar.wood.setText("" + newValue);
        });

        stone.addListener((observable, oldValue, newValue) -> {
            statusBar.stone.setText("" + newValue);
        });

        food.addListener((observable, oldValue, newValue) -> {
            statusBar.food.setText("" + newValue);
        });

        money.addListener((observable, oldValue, newValue) -> {
            statusBar.money.setText("" + newValue);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
