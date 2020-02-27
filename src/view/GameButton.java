package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.GameData;

import java.io.*;
import java.util.Scanner;

public class GameButton extends VBox {
    private GameData game;

    public GameButton(GameData game, GameGridView grid) {
        this.game = game;
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
        imageView.setFitWidth(grid.getSizeX() / 3 - 5);
        imageView.setFitHeight(grid.getSizeY() / 3 - 30);
        imageView.setOnMouseClicked(event -> launchGame());
        imageView.setStyle("-fx-shape: 'M0 0 L0 50 L25 25 L50 50 L50 0 Z';");
        Label label = new Label(game.getName());
        label.setStyle("-fx-font-weight: bold;-fx-font-size: 18pt;-fx-text-fill:white");
        getChildren().addAll(imageView, label);
        this.setAlignment(Pos.CENTER);
    }

    public void update(boolean isSelect) {
        if (isSelect) {
            setStyle("-fx-border-radius: 25px;fx-border-color: #000000;-fx-border-style: solid;-fx-border-width: 10;-fx-border: 2px;-fx-background-color: #FFA500");
        }else{
            setStyle("-fx-background-color: #FFA500;-fx-border-radius: 25px;");
        }
    }

    public void launchGame() {
        try
        {
            Runtime runtime = Runtime.getRuntime();
            // écrit la commande à exécuter dans le fichier run.sh
            FileWriter file = new FileWriter("./run.sh",false);
            file.write(game.getLaunch());
            file.close();

            Process p = runtime.exec("./run.sh");

            System.out.println(java.time.LocalTime.now());
            //TODO : Baisser la fenetre

            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ligne;
            while ((ligne = output.readLine()) != null)
            {
                System.out.println(ligne);
            }

            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((ligne = error.readLine()) != null)
            {
                System.out.println(ligne);
            }

            p.waitFor();

            System.out.println(java.time.LocalTime.now());
            //TODO : ouvrir la fenetre

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
