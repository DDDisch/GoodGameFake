package persons;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;

public class person extends ImageView {
    int attack, defense;
    private Image image;
    private SimpleIntegerProperty count = new SimpleIntegerProperty(0);
    private int foodCost;

    public person(Image elem, int attack, int defense, int foodCost) {
        super(elem);
        this.image = elem;
        this.attack = attack;
        this.defense = defense;
        this.foodCost = foodCost;
    }

    //Recrute new Player of the Type with a Count
    //@MICHI IGNORE
    public void build(int count) {
        if(Main.food.getValue() > (foodCost * count)) {
            this.count.setValue(this.count.getValue() + count);
            Main.food.setValue(Main.food.getValue() - foodCost*count);
        }
    }

    //Returns the Property of the Count Value can be used to Limit the Attackslider with a Max Value
    public SimpleIntegerProperty getCount() {
        return this.count;
    }

    public int getCountInteger() {
        return this.count.getValue();
    }

    public void decreaseCount(int decrease)
    {
        this.count.setValue(this.count.getValue()-decrease);
    }

    //Calculate Attack value of All Units of the Type
    public int calcAttackAll() {
        return attack*this.count.getValue();
    }

    //Calculate Attack value of a special count
    public int calcAttack(int count) {
        return attack*count;
    }

    public int calcDefenseAll() {
        return defense*count.getValue()+30; //30 for King in the Tower not raised by Leveling up the Tower which is possible at the current state of dev. but has no purpose
    }
}
