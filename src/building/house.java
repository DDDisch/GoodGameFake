package building;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

import java.util.Timer;
import java.util.TimerTask;

public class house extends ImageView {
    private int level = 1;
    private Image image;
    public double generate = 0.2;
    private double woodCost=250;
    public int defenseLevel = 3;
    private Timer timer = new Timer(true);

    public house() {
        super();
        if(Main.wood.getValue() >= woodCost) {
            this.setImage(new Image("images.buildings/house/Garage1.png"));
            Main.wood.setValue(Main.wood.getValue() - woodCost);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Main.wood.setValue(Main.wood.getValue()+generate);
                }
            }, 1000,1000);

        }
        this.setOnMousePressed(e -> this.nextLevel(Main.wood.getValue(), Main.stone.getValue()));
    }

    private void nextLevel(double wood, double stone) {
        if (level < 3) {
            woodCost *= 2;
            defenseLevel *= 2;
            generate *= 3;
            level++;
        }

        if(wood >= woodCost) {
            Main.wood.setValue(wood-woodCost);
            if(level == 2) {
                this.setImage(new Image("images.buildings/house/Garage2.png"));
            } else if(level == 3) {
                this.setImage(new Image("images.buildings/house/Garage3.png"));
            }
        } else {
            woodCost /= 2;
            generate /= 3;
        }
    }
}
