package building;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

import java.util.Timer;
import java.util.TimerTask;

public class foodFarm extends ImageView {
    private double generate = 0.3;
    private int level = 1;
    private Image image;
    private double woodCost=150, stoneCost=50;
    private Timer timer = new Timer(true);

    public foodFarm() {
        super();
        if(Main.wood.getValue() >= woodCost && Main.stone.getValue() >= stoneCost) {
            this.setImage(new Image("images.buildings/food/Farm1.png"));
            Main.wood.setValue(Main.wood.getValue() - woodCost);
            Main.stone.setValue(Main.stone.getValue() - stoneCost);
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.food.setValue(Main.food.getValue()+generate);
            }
        }, 1000,1000);

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

        if(wood >= woodCost && stone >= stoneCost) {
            Main.wood.setValue(wood-woodCost);
            Main.stone.setValue(stone-stoneCost);
            if(level == 2) {
                this.setImage(new Image("images.buildings/food/Farm2.png"));
            } else if(level == 3) {
                this.setImage(new Image("images.buildings/food/Farm3.png"));
            }
        } else {
            level--;
            generate = generate/3;
            woodCost /= 2;
            if(level != 1) {
                stoneCost /= 2;
            }
        }
    }
}
