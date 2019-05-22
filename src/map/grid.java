package map;

import building.foodFarm;
import building.stoneFarm;
import building.woodFarm;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class grid {
    public static void generateGrid(BorderPane root, int size,int width, int height, Stage primaryStage) {
        Rectangle rect = null;
        for (int x = 0, c = 0; x < size; x = x + width, c++) {
            for (int y = 0; y < size; y = y + height) {
                if (c % 2 == 0) {
                     rect = RectangleBuilder.create()
                            .width(width)
                            .height(height)
                            .x(x+10)
                            .y(y+50)
                            .style("-fx-fill: darkgreen;")
                            .build();
                } else {
                    rect = RectangleBuilder.create()
                            .width(width)
                            .height(height)
                            .x(x+10)
                            .y(y+50)
                            .style("-fx-fill: green;")
                            .build();
                }

                assert rect != null;
                root.getChildren().add(rect);
                addListener(rect, root, primaryStage);

                c++;
            }
        }
    }

    private static void addListener(Rectangle rect, BorderPane root, Stage primaryStage) {
        rect.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {

            final Popup dialog = new Popup();

            GridPane root2 = new GridPane();
            dialog.getContent().add(root2);
            root2.setMinWidth(500);
            root2.setMinHeight(400);

            root2.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));

            Button wood = new Button("Holzfäller", new ImageView(new Image("images.buildings/wood/Wood1.png")));
            Button stone = new Button("Steinbruch", new ImageView(new Image("images.buildings/stone/Iron1.png")));
            Button food = new Button("Bauernhof", new ImageView(new Image("images.buildings/food/Farm1.png")));
            Button soldier = new Button("Kaserne", new ImageView(new Image("images.buildings/soldier/Barracks1.png")));
            Button house = new Button("Wohnhaus", new ImageView(new Image("images.buildings/house/Garage1.png")));

            VBox vbox = new VBox(wood, stone, food, soldier, house);

            root2.getChildren().add(vbox);

            dialog.show(primaryStage);

            wood.setOnAction(j -> {
                woodFarm w = new woodFarm();
                w.setFitHeight(rect.getHeight());
                w.setFitWidth(rect.getWidth());
                w.setX(rect.getX());
                w.setY(rect.getY());
                root.getChildren().add(w);
                dialog.hide();
            });

            stone.setOnAction(j -> {
                stoneFarm w = new stoneFarm();
                w.setFitHeight(rect.getHeight());
                w.setFitWidth(rect.getWidth());
                w.setX(rect.getX());
                w.setY(rect.getY());
                root.getChildren().add(w);
                dialog.hide();
            });

            food.setOnAction(j -> {
                foodFarm w = new foodFarm();
                w.setFitHeight(rect.getHeight());
                w.setFitWidth(rect.getWidth());
                w.setX(rect.getX());
                w.setY(rect.getY());
                root.getChildren().add(w);
                dialog.hide();
            });

            soldier.setOnAction(j -> {
                //root.getChildren.add();
                dialog.hide();
            });

            house.setOnAction(j -> {
                //root.getChildren.add();
                dialog.hide();
            });
        });
    }
}
