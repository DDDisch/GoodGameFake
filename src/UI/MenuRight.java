package UI;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MenuRight extends VBox {

    private Stage primaryStage;

    public MenuRight(Stage primaryStage) {
        super();
        this.primaryStage = primaryStage;
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
            Text text = new Text("Welche Truppen sollen Angreife");
            //Slider einfügen
            root.getChildren().add(text);
        }

        dialog.show(primaryStage);
    }

    public VBox getVbox() {
        return this;
    }
}
