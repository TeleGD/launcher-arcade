package view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TopView extends HBox {
    public TopView(){
        setStyle("-fx-background-color: linear-gradient(to top,"+Launcher.PURPLE+","+Launcher.PURPLE_DARK+")");
        setPrefHeight(60);
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("res/images/Logo_TGD.png");
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image logo = new Image(inputstream);
        ImageView logoView = new ImageView(logo);
        getChildren().add(logoView);
        logoView.setFitWidth(60);
        this.setMargin(logoView,new Insets(0,5,0,5));
        logoView.setPreserveRatio(true);
    }
}
