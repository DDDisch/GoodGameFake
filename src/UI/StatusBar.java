package UI;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class StatusBar extends HBox {
    public TextField wood = new TextField();
    public TextField money = new TextField();
    public TextField stone = new TextField();
    public TextField food = new TextField();
    private ImageView woodImage = new ImageView(new Image(""));
    private ImageView moneyImage = new ImageView(new Image(""));
    private ImageView stoneImage = new ImageView(new Image(""));
    private ImageView foodImage = new ImageView(new Image(""));

    public StatusBar() {
        super();
        position();
    }

    private void position() {
        this.getChildren().addAll(woodImage, wood, moneyImage, money, stoneImage, stone, foodImage, food);
    }

    public HBox getHbox() {
        return this;
    }
}
