package building;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class house extends ImageView {
    private int level = 1;
    private Image image;
    private double woodCost=500, stoneCost=200;
    public int defenseLevel = 3;

    public house() {
        super();
        if(Main.wood.getValue() >= woodCost && Main.stone.getValue() >= stoneCost) {
            this.setImage(new Image("images.buildings/house/Garage1.png"));
            Main.wood.setValue(Main.wood.getValue() - woodCost);
            Main.stone.setValue(Main.stone.getValue() - stoneCost);
            Main.soldierBuild.set(true);
        }
        this.setOnMouseClicked(e -> this.nextLevel(Main.wood.getValue(), Main.stone.getValue()));
    }

    private void nextLevel(double wood, double stone) {
        if (level < 3) {
            woodCost *= 2;
            stoneCost *= 2;
            defenseLevel *= 2;
            level++;
        }

        if(wood >= woodCost && stone >= stoneCost) {
            Main.wood.setValue(wood-woodCost);
            Main.stone.setValue(stone-stoneCost);
            if(level == 2) {
                this.setImage(new Image("images.buildings/house/Garage2.png"));
            } else if(level == 3) {
                this.setImage(new Image("images.buildings/house/Garage3.png"));
            }
        } else {
            woodCost /= 2;
            if(level != 1) {
                stoneCost /= 2;
            }
        }
    }
}
