package building;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

import java.util.Timer;
import java.util.TimerTask;

public class woodFarm extends ImageView {
    private double generate = 0.5;
    private int level = 1;
    private Image image;
    private double woodCost=150, stoneCost=0;
    private Timer timer = new Timer(true);

    public woodFarm() {
        super();
        if(Main.wood.getValue() >= woodCost && Main.stone.getValue() >= stoneCost) {
            this.setImage(new Image("images.buildings/wood/Wood1.png"));
            Main.wood.setValue(Main.wood.getValue() - woodCost);
            Main.stone.setValue(Main.stone.getValue() - stoneCost);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Main.wood.setValue(Main.wood.getValue()+generate);
                }
            }, 1000,1000);
        }


        this.setOnMouseClicked(e -> this.nextLevel(Main.wood.getValue(), Main.stone.getValue()));
    }

    private void nextLevel(double wood, double stone) {
        if (level < 3) {
            if (level == 1) {
                woodCost *= 2;
                stoneCost = 300;
                generate *= 3;
                level = level + 1;
            } else {
                woodCost *= 2;
                stoneCost *= 2;
                generate *= 3;
                level = level + 1;
            }

            if (wood >= woodCost && stone >= stoneCost) {
                Main.wood.setValue(wood - woodCost);
                Main.stone.setValue(stone - stoneCost);

                if (level == 2) {
                    this.setImage(new Image("images.buildings/wood/Wood2.png"));
                }
                if (level == 3) {
                    this.setImage(new Image("images.buildings/wood/Wood3.png"));
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
}
