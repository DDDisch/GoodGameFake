package map;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;

public class grid {
    public static void generateGrid(BorderPane root, int size,int width, int height) {
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

                c++;
            }
        }
    }
}
