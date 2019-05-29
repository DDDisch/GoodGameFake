package UI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class StatusBar extends HBox {
    public Label wood = new Label();
    public Label money = new Label();
    public Label stone = new Label();
    public Label food = new Label();
    private ImageView woodImage = new ImageView(new Image("images/icon/wood.png"));
    private ImageView moneyImage = new ImageView(new Image("images/icon/money.png"));
    private ImageView stoneImage = new ImageView(new Image("images/icon/stone.png"));
    private ImageView foodImage = new ImageView(new Image("images/icon/food.png"));

    public StatusBar() {
        super();
        position();

        this.setAlignment(Pos.CENTER);

        woodImage.setFitHeight(20);
        woodImage.setFitWidth(20);

        moneyImage.setFitHeight(20);
        moneyImage.setFitWidth(20);

        stoneImage.setFitHeight(20);
        stoneImage.setFitWidth(20);

        foodImage.setFitHeight(20);
        foodImage.setFitWidth(20);
    }

    private void position() {
        this.getChildren().addAll(woodImage, wood, stoneImage, stone, foodImage, food, moneyImage, money);
    }

    public HBox getHbox() {
        return this;
    }
}
