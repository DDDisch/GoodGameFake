package map;

import building.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Random;

public class grid implements EventHandler<MouseEvent> {
    private BorderPane root = null;
    private Stage primaryStage = null;

    public void generateGrid(BorderPane root, int size, int width, int height, Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.root = root;

        ImageView rect = null;
        int randFortressPlace = new Random().nextInt(109);
        for (int x = 0, c = 0; x < size; x = x + width, c++) {
            for (int y = 0; y < size; y = y + height) {
                rect = ImageViewBuilder.create()
                        .fitWidth(width)
                        .fitHeight(height)
                        .x(x + 10)
                        .y(y + 50)
                        .image(new Image("images/icon/grass.jpg"))
                        .build();

                assert rect != null;
                root.getChildren().add(rect);
                addListener(rect);

                if (c == randFortressPlace) {
                    fortress w = new fortress();
                    w.setFitHeight(rect.getFitHeight());
                    w.setFitWidth(rect.getFitWidth());
                    w.setX(rect.getX());
                    w.setY(rect.getY());
                    root.getChildren().add(w);
                    rect.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                }

                c++;
            }
        }
    }

    private void addListener(ImageView rect) {
        EventHandler<MouseEvent> tmp = null;

        rect.addEventFilter(MouseEvent.MOUSE_PRESSED, this);
    }

    @Override
    public void handle(MouseEvent event) {
        ImageView iv = (ImageView)event.getSource();
        final Popup dialog = new Popup();

        GridPane root2 = new GridPane();
        dialog.getContent().add(root2);

        root2.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));

        ImageView woodIV = new ImageView(new Image("images.buildings/wood/Wood1.png"));
        ImageView stoneIV = new ImageView(new Image("images.buildings/stone/Iron1.png"));
        ImageView foodIV = new ImageView(new Image("images.buildings/food/Farm1.png"));
        ImageView soldierIV = new ImageView(new Image("images.buildings/soldier/Barracks1.png"));
        ImageView houseIV = new ImageView(new Image("images.buildings/house/Garage1.png"));

        woodIV.setFitHeight(20);
        woodIV.setPreserveRatio(true);

        stoneIV.setFitHeight(20);
        stoneIV.setPreserveRatio(true);

        foodIV.setFitHeight(20);
        foodIV.setPreserveRatio(true);

        soldierIV.setFitHeight(20);
        soldierIV.setPreserveRatio(true);

        houseIV.setFitHeight(20);
        houseIV.setPreserveRatio(true);

        Button wood = new Button("Holzfäller", woodIV);
        Button stone = new Button("Steinbruch", stoneIV);
        Button food = new Button("Bauernhof", foodIV);
        Button soldier = new Button("Kaserne", soldierIV);
        Button house = new Button("Wohnhaus", houseIV);

        Text woodInfo = new Text("Building Cost: 150 Wood");
        Text stoneInfo = new Text("Building Cost: 250 Wood & 100 Stone");
        Text foodInfo = new Text("Building Cost: 150 Wood & 50 Stone");
        Text soldierInfo = new Text("Building Cost: 500 Wood & 200 Stone");
        Text houseInfo = new Text("Building Cost: 150 Wood");


        VBox vbox = new VBox(new HBox(wood, woodInfo), new HBox(stone, stoneInfo), new HBox(food, foodInfo), new HBox(soldier, soldierInfo), new HBox(house, houseInfo));

        root2.getChildren().add(vbox);

        dialog.show(primaryStage);

        wood.setOnAction(j -> {
            woodFarm w = new woodFarm();
            if (w.getImage() != null) {
                w.setFitHeight(iv.getFitHeight());
                w.setFitWidth(iv.getFitWidth());
                w.setX(iv.getX());
                w.setY(iv.getY());
                root.getChildren().add(w);
                iv.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
            } else {
                w = null;
                System.gc();
            }
            dialog.hide();
        });

        stone.setOnAction(j -> {
            stoneFarm w = new stoneFarm();
            if (w.getImage() != null) {
                w.setFitHeight(iv.getFitHeight());
                w.setFitWidth(iv.getFitWidth());
                w.setX(iv.getX());
                w.setY(iv.getY());
                root.getChildren().add(w);
                iv.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
            } else {
                w = null;
                System.gc();
            }
            dialog.hide();
        });

        food.setOnAction(j -> {
            foodFarm w = new foodFarm();
            if (w.getImage() != null) {
                w.setFitHeight(iv.getFitHeight());
                w.setFitWidth(iv.getFitWidth());
                w.setX(iv.getX());
                w.setY(iv.getY());
                root.getChildren().add(w);
                iv.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
            } else {
                w = null;
                System.gc();
            }
            dialog.hide();
        });

        soldier.setOnAction(j -> {
            barracks w = new barracks();
            if (w.getImage() != null) {
                w.setFitHeight(iv.getFitHeight());
                w.setFitWidth(iv.getFitWidth());
                w.setX(iv.getX());
                w.setY(iv.getY());
                root.getChildren().add(w);
                iv.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
            } else {
                w = null;
                System.gc();
            }
            dialog.hide();
        });

        house.setOnAction(j -> {
            house w = new house();
            if (w.getImage() != null) {
                w.setFitHeight(iv.getFitHeight());
                w.setFitWidth(iv.getFitWidth());
                w.setX(iv.getX());
                w.setY(iv.getY());
                root.getChildren().add(w);
                iv.removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
            } else {
                w = null;
                System.gc();
            }
            dialog.hide();
        });
    }
}
