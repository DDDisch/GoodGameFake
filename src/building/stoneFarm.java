package building;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class stoneFarm extends ImageView {
    private double generate = 0.2;
    private int level = 1;
    private Image image;
    private double woodCost=150, stoneCost=0;

    public stoneFarm() {
        super(new Image("images.buildings/stone/Iron1.png"));
        this.setOnMouseClicked(e -> this.nextLevel(Main.wood.getValue(), Main.stone.getValue()));
    }

    private void nextLevel(double wood, double stone) {
        if (level < 3) {
            if(level == 1) {
                woodCost *= 2;
                stoneCost = 300;
                generate *= 3;
                level ++;
            } else {
                woodCost *= 2;
                stoneCost *= 2;
                generate *= 3;
                level++;
            }
        }

        if(wood > woodCost && stone > stoneCost) {
            if(level == 2) {
                this.setImage(new Image("images.buildings/stone/Iron2.png"));
            } else if(level == 3) {
                this.setImage(new Image("images.buildings/stone/Iron3.png"));
            }
        } else {
            level--;
        }
    }
}
