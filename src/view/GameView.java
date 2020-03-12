package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    }

    public void update(){
        getChildren().clear();
        GameButton button = (GameButton)gameGridView.getChildren().get(gameGridView.buttonSelected());
        game = button.getGame();
        this.setWidth(Launcher.getInstance().getSizeX()/4);
        this.setHeight(Launcher.getInstance().getSizeY()-120);
        this.setAlignment(Pos.TOP_CENTER);
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
        imageView.setFitWidth(this.getWidth()-70);
        this.setMargin(imageView,new Insets(30,15,15,15));
        imageView.setPreserveRatio(true);

        // titre et description
        Label name = new Label(game.getName());
        name.setStyle("-fx-font: bold italic 20pt 'Arial'; " +
                "-fx-text-alignment: center;"+
                "-fx-text-fill: white");
        name.setPrefHeight(40);
        Label description = new Label(game.getDescription()+"\n");
        description.setWrapText(true);
        description.setStyle("-fx-font: 13pt 'Arial'; " +
                "-fx-text-alignment: center;" +
                "-fx-text-fill: white;" +
                "-fx-max-width:" + (Launcher.getInstance().getSizeX()/4 -40) +";");
        getChildren().addAll(name,description);

        // autres images
        GridPane grid = new GridPane();
        getChildren().add(grid);
        int i = 0;
        for(String img:game.getImages()){
            if(i!=0) {
                inputstream = null;
                try {
                    inputstream = new FileInputStream(img);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                image = new Image(inputstream);
                imageView = new ImageView();
                imageView.setImage(image);
                grid.setMargin(imageView, new Insets(5, 10, 5, 10));
                grid.add(imageView, (i-1) % 2, (i-1) / 2);
                imageView.setFitWidth(this.getWidth() / 2 - 40);
                imageView.setPreserveRatio(true);
            }i += 1;
        }
    }
}
