package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.GameData;

public class Launcher {
    private static Launcher instance;
    public static final String PURPLE =  "#6C2466";

    private Scene scene;

    public Launcher() {
        instance = this;
    }

    public void start(Stage primaryStage) throws Exception{
        GameData game = new GameData("Grave-et", "meilleur jeu de tous les temps","",new String[]{"res/test/image1.jpg","res/test/image2.jpg"});

        primaryStage.setTitle("Launcher TGD");
        BorderPane root = new BorderPane();
        scene = new Scene(root, 1280, 720);
        root.setTop(new TopView());
        root.setBottom(new HelpView());
        root.setCenter(new GameGridView());
        GameView gameView = new GameView(game);
        root.setRight(gameView);
        gameView.update();


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

    public void getGames(){

    }
}