package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.GameData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameView extends VBox {
    private GameData game;
    public GameView(GameData game){
        this.game = game;
        setStyle("-fx-background-color: "+Launcher.PURPLE);
        this.setPrefWidth(400);
    }

    public void update(){
        getChildren().clear();
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(game.getImages()[0]);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        getChildren().add(imageView);
        imageView.setFitWidth(370);
        imageView.setPreserveRatio(true);
        this.setAlignment(Pos.TOP_CENTER);

        Label name = new Label(game.getName());
        name.setTextFill(Color.WHITE);
        name.setFont(new Font("Arial",30));
        name.setPrefHeight(40);
        Label description = new Label(game.getDescription());
        description.setTextFill(Color.WHITE);
        getChildren().addAll(name,description);
    }
}
