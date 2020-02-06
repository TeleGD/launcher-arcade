package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Launcher {
    private static Launcher instance;

    private Scene scene;

    public void start(Stage primaryStage) throws Exception{
        instance = this;
        primaryStage.setTitle("Launcher TGD");

        GridPane root = new GridPane();
        scene = new Scene(root, 1280, 720);

        int number = 20;
        for (int i = 0; i<number; i++){
            GameButton gameButton = new GameButton("test"+i,i%2==0?"res/test/image1.jpg":"res/test/image2.jpg");
            root.setMargin(gameButton,new Insets(5,5,5,5));
            root.add(gameButton, i%3, i/3);
        }

        //scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Launcher getInstance() {
        return instance;
    }

    public double getSizeX(){
        return scene.getWidth();
    }

    public double getSizeY(){
        return scene.getHeight();
    }
}