package sample;

import UI.Log;
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
import persons.person;

public class Main extends Application {
    MenuRight menuRight;
    StatusBar statusBar = new StatusBar();
    public static SimpleDoubleProperty wood = new SimpleDoubleProperty();
    public static SimpleDoubleProperty stone = new SimpleDoubleProperty();
    public static SimpleDoubleProperty food = new SimpleDoubleProperty();
    public static SimpleDoubleProperty money = new SimpleDoubleProperty();
    static PreConfig preConfig;

    //IMAGES ARE CURRENTLY NOT USED CAUSE I HAVE NOT FOUND ONE IN THE RIGHT STYLE AND ARE TO LARGE JUST FOR LATER INTEGRATION INCLUDED
    //ARE STATIC TO ACCESS THEM IN OTHER CLASSES TO CHANGE THE VALUES
    public static person sword = new person(new Image("images/icon/sword.png"), 5,4,75);
    public static person spear = new person(new Image("images/icon/sword.png"), 8,5,125);
    public static person bow = new person(new Image("images/icon/sword.png"), 7,7,150);
    public static person crossbow = new person(new Image("images/icon/sword.png"), 8,6,175);

    public static SimpleBooleanProperty soldierBuild = new SimpleBooleanProperty(false);
    public static SimpleBooleanProperty buyResources = new SimpleBooleanProperty(false);

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        new grid().generateGrid(root, 500,50,50, primaryStage);
        menuRight = new MenuRight(primaryStage);
        primaryStage.setTitle("Strategy Game");
        primaryStage.setScene(new Scene(root, 750, 600));
        primaryStage.setResizable(false);
        primaryStage.show();

        new Log();

        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }
        });

        addListener();

        wood.set(500);
        stone.set(200);
        money.set(1000);
        food.set(300);

        root.setRight(menuRight.getVbox());
        root.setTop(statusBar.getHbox());
        preConfig = new PreConfig();


    }

    private void addListener() {
        wood.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> statusBar.wood.setText("" + (int)Math.round(wood.getValue()))));

        stone.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> statusBar.stone.setText("" + (int)Math.round(stone.getValue()))));

        food.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> statusBar.food.setText("" + (int)Math.round(food.getValue()))));

        money.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> statusBar.money.setText("" + (int)Math.round(money.getValue()))));

        soldierBuild.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            menuRight.createMenuItem("Soldier",new Image("images/icon/helmet.png"), 2 );
            //CHANGE THE FUNCTION OF THE BUTTON IN THE MENU RIGHT CLASS (AREA IS MARKED WITH AN COMMENTARY)
            menuRight.createMenuItem("Attack", new Image("images/icon/sword.png"), 3);
        }));

        buyResources.addListener((observable, oldValue, newValue) -> Platform.runLater(() -> {
            menuRight.createMenuItem("Buy Ressources", 4);
        }));
    }

    static public void write(String input)
    {
        Thread t = new Thread(()->{

            while(!preConfig.isConnectedBoolean()) {
                System.out.print("");
            }
                if(preConfig.getServer() != null)
                {
                    preConfig.getServer().write(input);
                }

                if (preConfig.getClient() != null)
                {
                    preConfig.getClient().write(input);
                }
        });
        t.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
