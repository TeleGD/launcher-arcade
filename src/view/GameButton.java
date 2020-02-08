package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.*;

public class GameButton extends VBox {

    //private GameData game;

    public GameButton(String s, String imgPath, GameGridView grid) {

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
        imageView.setFitWidth(grid.getSizeX() / 3 - 15);
        imageView.setFitHeight(grid.getSizeY() / 3 - 30);
        imageView.setOnMouseClicked(event -> launchGame());

        Label label = new Label(s);

        getChildren().addAll(imageView, label);
        this.setAlignment(Pos.CENTER);
    }

    public void launchGame() {
        try
        {
            Runtime runtime = Runtime.getRuntime();
            Process p = runtime.exec("../tgd-grave-et/run.sh");

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
