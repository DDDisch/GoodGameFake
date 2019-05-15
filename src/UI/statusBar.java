package UI;

import javafx.beans.property.SimpleIntegerProperty;

public class statusBar {
    SimpleIntegerProperty wood = new SimpleIntegerProperty();
    SimpleIntegerProperty money = new SimpleIntegerProperty();
    SimpleIntegerProperty stone = new SimpleIntegerProperty();
    SimpleIntegerProperty food = new SimpleIntegerProperty();

    public statusBar(SimpleIntegerProperty wood, SimpleIntegerProperty money, SimpleIntegerProperty stone, SimpleIntegerProperty food) {
        this.wood.bindBidirectional(wood);
        this.money.bindBidirectional(money);
        this.stone.bindBidirectional(stone);
        this.food.bindBidirectional(food);
    }
}
