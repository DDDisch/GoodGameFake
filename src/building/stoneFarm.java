package building;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

import java.util.Timer;
import java.util.TimerTask;

public class stoneFarm extends ImageView {
    private double generate = 0.2;
    private int level = 1;
    private Image image;
    private double woodCost=250, stoneCost=100;
    private Timer timer = new Timer(true);

    public stoneFarm() {
        super();
        if(Main.wood.getValue() >= woodCost && Main.stone.getValue() >= stoneCost) {
            this.setImage(new Image("images.buildings/stone/Iron1.png"));
            Main.wood.setValue(Main.wood.getValue() - woodCost);
            Main.stone.setValue(Main.stone.getValue() - stoneCost);
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Main.stone.setValue(Main.stone.getValue()+generate);
            }
        }, 1000,1000);

        this.setOnMouseClicked(e -> this.nextLevel(Main.wood.getValue(), Main.stone.getValue()));
    }

    private void nextLevel(double wood, double stone) {
        if (level < 3) {
            woodCost *= 2;
            stoneCost *= 2;
            generate *= 3;
            level++;
        }

        if(wood >= woodCost && stone >= stoneCost) {
            Main.wood.setValue(wood-woodCost);
            Main.stone.setValue(stone-stoneCost);
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
