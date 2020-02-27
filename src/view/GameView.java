package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.GameData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameView extends VBox {
    private GameGridView gameGridView;
    private GameData game;
    public GameView(GameData game,GameGridView gameGridView){
        this.gameGridView = gameGridView;
        this.game = game;
        setStyle("-fx-background-color: "+Launcher.PURPLE);
        //this.setPrefWidth();
        this.setWidth(Launcher.getInstance().getSizeX()/4);
        this.setHeight(Launcher.getInstance().getSizeY()-100);
    }

    public void update(GameData game){
        this.game = game;
        getChildren().clear();

        // image de présentation
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(game.getImages().get(0));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputstream);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        getChildren().add(imageView);
        imageView.setFitWidth(this.getWidth()-40);
        this.setMargin(imageView,new Insets(30,5,5,5));
        imageView.setPreserveRatio(true);
        this.setAlignment(Pos.TOP_CENTER);

        // titre et description
        Label name = new Label(game.getName());
        name.setTextFill(Color.WHITE);
        name.setFont(new Font("Arial",30));
        name.setPrefHeight(40);
        Label description = new Label(game.getDescription());
        description.setTextFill(Color.WHITE);
        getChildren().addAll(name,description);

        // autres images
        GridPane grid = new GridPane();
        getChildren().add(grid);
        int i = 0;
        for(String img:game.getImages()){
            inputstream = null;
            try {
                inputstream = new FileInputStream(img);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            image = new Image(inputstream);
            imageView = new ImageView();
            imageView.setImage(image);
            grid.setMargin(imageView,new Insets(5,5,5,5));
            grid.add(imageView,i%2,i/2);
            imageView.setFitWidth(this.getWidth()/2 - 20);
            imageView.setPreserveRatio(true);
            i+=1;
        }
    }
}
