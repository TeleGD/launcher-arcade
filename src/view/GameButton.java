package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static view.Main.getSizeX;


public class GameButton extends VBox {
    private static final double IMAGE_WIDTH = getSizeX() - 6*5;
    private static final double IMAGE_HEIGHT = Main.getSizeY() - 6*5;

    //private GameData game;

    public GameButton(String s, String imgPath) {

        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(imgPath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image image = new Image(inputstream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(IMAGE_WIDTH/3);
        imageView.setFitHeight(IMAGE_HEIGHT/3);

        Label label = new Label(s);

        getChildren().addAll(imageView, label);
        this.setAlignment(Pos.CENTER);


    }
}
