package building;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class woodFarm extends ImageView {
    public double generate = 0.2;
    private int level = 1;
    private Image image;
    private double woodCost=150, stoneCost=0;

    public woodFarm() {
        super();
        this.setOnMouseClicked(e -> this.nextLevel(Main.wood.getValue(), Main.stone.getValue()));
    }

    private void nextLevel(double wood, double stone) {
        if (level < 3) {
            if(level == 2) {
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
            if(level == 1) {
                this.setImage(new Image("images.buildings/wood/Wood1.png"));
            } else if(level == 2) {
                this.setImage(new Image("images.buildings/wood/Wood2.png"));
            } else if(level == 3) {
                this.setImage(new Image("images.buildings/wood/Wood3.png"));
            }
        } else {
            level--;
        }
    }
}
