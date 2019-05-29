package sample;

import UI.MenuRight;
import UI.PreConfig;
import UI.StatusBar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import map.grid;

public class Main extends Application {
    MenuRight menuRight;
    StatusBar statusBar = new StatusBar();
    public static SimpleDoubleProperty wood = new SimpleDoubleProperty();
    public static SimpleDoubleProperty stone = new SimpleDoubleProperty();
    public static SimpleDoubleProperty food = new SimpleDoubleProperty();
    public static SimpleDoubleProperty money = new SimpleDoubleProperty();
    PreConfig preConfig;

    public static SimpleBooleanProperty soldierBuild = new SimpleBooleanProperty(false);

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        grid.generateGrid(root, 500,50,50, primaryStage);
        menuRight = new MenuRight(primaryStage);
        primaryStage.setTitle("Strategy Game");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode().equals(KeyCode.ESCAPE)) {
                Platform.exit();
            }
        });

        menuRight.createMenuItem("Attack", new Image("images/icon/sword.png"), 3);

        addListener();

        wood.set(1000);
        stone.set(1000);
        money.set(1000);
        food.set(1000);

        root.setRight(menuRight.getVbox());
        root.setTop(statusBar.getHbox());
        preConfig = new PreConfig();

       write("Disch");

    }

    private void addListener() {
        wood.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> statusBar.wood.setText("" + (int)Math.round(wood.getValue()))));

        stone.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> statusBar.stone.setText("" + (int)Math.round(stone.getValue()))));

        food.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> statusBar.food.setText("" + (int)Math.round(food.getValue()))));

        money.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> statusBar.money.setText("" + (int)Math.round(money.getValue()))));

        soldierBuild.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> menuRight.createMenuItem("Soldier",new Image("images/icon/helmet.png"), 2 )));
    }

    public void write(String input)
    {
        Thread t = new Thread(()->{
            while(!preConfig.isConnectedBoolean()) {}
            if (preConfig.isConnectedBoolean())
            {
                if(preConfig.getServer() != null)
                {
                    preConfig.getServer().write(input);
                }

                if (preConfig.getClient() != null)
                {
                    preConfig.getClient().write(input);
                }
            }
            else
            {
                System.out.println("Could not write content. No connection found.");
            }
        });
        t.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
