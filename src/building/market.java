package building;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class market extends ImageView {
    private int level = 1;
    private Image image;
    private double woodCost=300, stoneCost=200;

    public market() {
        super();
        if(Main.wood.getValue() >= woodCost && Main.stone.getValue() >= stoneCost) {
            this.setImage(new Image("images.buildings/market/Market1.png"));
            Main.wood.setValue(Main.wood.getValue() - woodCost);
            Main.stone.setValue(Main.stone.getValue() - stoneCost);
            Main.buyResources.set(true);
        }
    }
}
