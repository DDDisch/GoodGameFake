package building;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class fortress extends ImageView {
    private int level = 1;
    private Image image;
    private double woodCost=1000, stoneCost=500;

    public fortress() {
        super();
        this.setImage(new Image("images.buildings/fortress/Main1.png"));
        Main.wood.setValue(Main.wood.getValue() - woodCost);
        Main.stone.setValue(Main.stone.getValue() - stoneCost);
        this.setOnMouseClicked(e -> this.nextLevel(Main.wood.getValue(), Main.stone.getValue()));
    }

    private void nextLevel(double wood, double stone) {
        if (level < 3) {
            woodCost *= 2;
            stoneCost *= 2;
            level++;
        }

        if(wood >= woodCost && stone >= stoneCost) {
            Main.wood.setValue(wood-woodCost);
            Main.stone.setValue(stone-stoneCost);
            if(level == 2) {
                this.setImage(new Image("images.buildings/soldier/Main2.png"));
            } else if(level == 3) {
                this.setImage(new Image("images.buildings/food/Main3.png"));
            }
        } else {
            level--;
            if(level == 2) {
                woodCost /= 2;
                stoneCost /= 2;
            }
        }
    }
}
