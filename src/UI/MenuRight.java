package UI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import sample.Main;

public class MenuRight extends VBox {

    private Stage primaryStage;
    public int attackPower;
    int stoneNum, woodNum, foodNum;
    int stoneCost = 8;
    int woodCost = 5;
    int foodCost = 15;


    public MenuRight(Stage primaryStage) {
        super();
        this.primaryStage = primaryStage;

        VBox vbox = new VBox();
        vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        Text sword = new Text("Sword: Atk: " + Main.sword.attack + " Def: " + Main.sword.defense + " Cost: " + Main.sword.foodCost);
        Text spear = new Text("Spear: Atk: " + Main.spear.attack + " Def: " + Main.spear.defense + " Cost: " + Main.spear.foodCost);
        Text bow = new Text("Bow: Atk: " + Main.bow.attack + " Def: " + Main.bow.defense + " Cost: " + Main.bow.foodCost);
        Text crossbow = new Text("Crossbow: Atk: " + Main.crossbow.attack + " Def: " + Main.crossbow.defense + " Cost: " + Main.crossbow.foodCost);
        vbox.getChildren().addAll(sword, spear, bow, crossbow);

        this.getChildren().add(vbox);
    }

    public void createMenuItem(String title,int mode) {
        Button button = new Button(title);
        this.getChildren().add(button);
        button.setOnAction(e -> this.generateListener(mode));
    }

    public void createMenuItem(String title, Image image, int mode) {
        ImageView iv = new ImageView(image);
        iv.setFitHeight(20);
        iv.setFitWidth(20);
        Button button = new Button(title, iv);
        this.getChildren().add(button);
        button.setOnAction(e -> this.generateListener(mode));
    }

    private void generateListener(int mode) {
        final Popup dialog = new Popup();

        GridPane root = new GridPane();
        dialog.getContent().add(root);

        if(mode == 3) {

            attackPower = Main.sword.calcAttackAll();
            attackPower = Main.bow.calcAttackAll() + attackPower;
            attackPower = Main.crossbow.calcAttackAll() + attackPower;
            attackPower = Main.spear.calcAttackAll() + attackPower;

            Text text = new Text("Welche Truppen sollen Angreife");

            Slider swordSlider = new Slider();
            Slider crossbowSlider = new Slider();
            Slider bowSlider = new Slider();
            Slider spearSlider = new Slider();


            initSlider(crossbowSlider);
            initSlider(bowSlider);
            initSlider(spearSlider);
            initSlider(swordSlider);

            swordSlider.setMax(Main.sword.getCountInteger());
            crossbowSlider.setMax(Main.crossbow.getCountInteger());
            bowSlider.setMax(Main.bow.getCountInteger());
            spearSlider.setMax(Main.spear.getCountInteger());

            Button attack = new Button("Attack!");

            attack.setOnAction(event -> {

                attackPower = Main.sword.calcAttack((int)swordSlider.getValue());
                attackPower = Main.bow.calcAttack((int)bowSlider.getValue()) + attackPower;
                attackPower = Main.crossbow.calcAttack((int)crossbowSlider.getValue()) + attackPower;
                attackPower = Main.spear.calcAttack((int)spearSlider.getValue()) + attackPower;


                if(attackPower == 0)
                {
                    Log.addLogEvent("Select some troops to attack enemy");
                }
                else {

                    Main.write("!" + attackPower);

                    Log.addLogEvent("You attacked with " + attackPower + " attack power!");

                    Main.sword.decreaseCount((int) swordSlider.getValue());
                    Main.crossbow.decreaseCount((int) crossbowSlider.getValue());
                    Main.bow.decreaseCount((int) bowSlider.getValue());
                    Main.spear.decreaseCount((int) spearSlider.getValue());

                    dialog.hide();
                }

            });

            int defPower = 50;
            defPower = Main.sword.calcDefenseAll() + defPower;
            defPower = Main.spear.calcDefenseAll() + defPower;
            defPower = Main.bow.calcDefenseAll() + defPower;
            defPower = Main.crossbow.calcDefenseAll() + defPower;

            Text atkdef = new Text("Atk: " + attackPower + " Def: " + defPower);

            VBox attackSlider = new VBox();
            attackSlider.getChildren().addAll(text,swordSlider,spearSlider,bowSlider,crossbowSlider,attack, atkdef);
            root.getChildren().add(attackSlider);

        } else if(mode == 2) {
            Text soldier = new Text("Übersicht über deine Soldaten und neue zum Ausbilden");

            Text ivSword = new Text("Schwert(" + Main.sword.getCount().getValue() + "): ");
            Text ivSpear = new Text("Speer(" + Main.spear.getCount().getValue() + "): ");
            Text ivBow = new Text("Bogen(" + Main.bow.getCount().getValue() + "): ");
            Text ivCrossbow = new Text("Armbrust(" + Main.crossbow.getCount().getValue() + "): ");

            Slider sword = new Slider();
            Slider spear = new Slider();
            Slider bow = new Slider();
            Slider crossbow = new Slider();
            initSlider(sword);
            initSlider(spear);
            initSlider(bow);
            initSlider(crossbow);


            Button recruteNew = new Button("Soldaten ausbilden! (Max 10 pro Typ)");

            GridPane.setConstraints(soldier, 0,0,2,1);
            GridPane.setConstraints(ivSword, 0,1);
            GridPane.setConstraints(ivSpear, 0,2);
            GridPane.setConstraints(ivBow, 0,3);
            GridPane.setConstraints(ivCrossbow, 0,4);
            GridPane.setConstraints(sword, 1,1);
            GridPane.setConstraints(spear, 1,2);
            GridPane.setConstraints(bow, 1,3);
            GridPane.setConstraints(crossbow, 1,4);
            GridPane.setConstraints(recruteNew, 0,5, 2, 1);
            GridPane.setHalignment(recruteNew, HPos.CENTER);

            root.getChildren().addAll(soldier, ivSword, ivSpear, ivBow, ivCrossbow, sword, spear, bow, crossbow, recruteNew);

            recruteNew.setOnAction(e -> {
                if(sword.getValue() > 0) {
                    Main.sword.build((int)sword.getValue());
                }
                if(spear.getValue() > 0) {
                    Main.spear.build((int)spear.getValue());
                }
                if(bow.getValue() > 0) {
                    Main.bow.build((int)bow.getValue());
                }
                if(crossbow.getValue() > 0) {
                    Main.crossbow.build((int)crossbow.getValue());
                }
                dialog.hide();
            });
        } else if(mode == 4) {
            //Buy Resource Pop Up goes here


            VBox buying = new VBox();



            HBox food = new HBox();
            Text typeFood = new Text("Food: ");
            TextField foodField = new TextField("0");
            Text foodText = new Text(" --> " + 0 + " Gold");
            food.getChildren().addAll(typeFood, foodField,foodText);

            HBox wood = new HBox();
            Text typeWood = new Text("Wood: ");
            TextField woodField = new TextField("0");
            Text woodText = new Text(" --> " + 0 + " Gold");
            wood.getChildren().addAll(typeWood, woodField, woodText);

            HBox stone = new HBox();
            Text typeStone = new Text("Stone ");
            TextField stoneField = new TextField("0");
            Text stoneText = new Text(" --> " + 0 + " Gold");
            stone.getChildren().addAll(typeStone, stoneField,stoneText);

            Button buyResource = new Button("Buy!");

            buying.getChildren().addAll(food, wood, stone,buyResource);
            root.getChildren().add(buying);

            foodField.textProperty().addListener((observable, oldValue, newValue) -> {
                foodNum = Integer.parseInt(foodField.getText());
                foodText.setText("-->" + (foodNum*foodCost) + "Gold");
            });

            woodField.textProperty().addListener((observable, oldValue, newValue) -> {
                woodNum = Integer.parseInt(woodField.getText());
                woodText.setText("-->" + (woodNum*woodCost) + "Gold");
            });

            stoneField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                stoneNum = Integer.parseInt(stoneField.getText());
                stoneText.setText("-->" + (stoneNum*stoneCost) + "Gold");
            });

            buyResource.setOnAction(event -> {
                int cost = (stoneNum * stoneCost) + (woodNum * woodCost) + (foodNum * foodCost);

                Main.money.set(Main.money.getValue() - cost);
                Main.wood.set(Main.wood.getValue() + woodNum);
                Main.food.set(Main.food.getValue() + foodNum);
                Main.stone.set(Main.stone.getValue() + stoneNum);


                woodField.setText("0");
                stoneField.setText("0");
                foodField.setText("0");

            });


        }

        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        dialog.show(primaryStage);
    }

    private void initSlider(Slider tmp) {
        tmp.setMax(10);
        tmp.setMin(0);
        tmp.setSnapToTicks(true);
        tmp.setShowTickLabels(true);
        tmp.setMinorTickCount(0);
        tmp.setBlockIncrement(1);
        tmp.setMajorTickUnit(1.0);
    }

    public VBox getVbox() {
        return this;
    }
}
