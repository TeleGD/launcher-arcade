package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameButton extends VBox {

    //private GameData game;

    public GameButton(String s, String imgPath) {

        //temporaire, a terme on lira la texture dans GameData
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
        imageView.setFitWidth(Launcher.getInstance().getSizeX() / 3 - 15);
        imageView.setFitHeight(Launcher.getInstance().getSizeY() / 3 - 15);

        Label label = new Label(s);

        getChildren().addAll(imageView, label);
        this.setAlignment(Pos.CENTER);


    }
}
