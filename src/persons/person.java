package persons;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class person extends ImageView {
    int attack, defense;
    private Image image;

    public person(Image elem, int attack, int defense) {
        super(elem);
        this.image = elem;
        this.attack = attack;
        this.defense = defense;
    }

    public int calcAttack() {
        return attack;
    }

    public int calcDefense() {
        return defense;
    }
}
