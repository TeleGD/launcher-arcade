package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.GameData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Launcher {
    private static Launcher instance;
    public static final String PURPLE =  "#6C2466";

    private Scene scene;

    private ArrayList<GameData> gameData;

    public Launcher() {
        instance = this;
    }

    public void start(Stage primaryStage) throws Exception{
        gameData = new ArrayList<GameData>();
        findGamesData();
        GameData game = gameData.get(0);
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

    public ArrayList<GameData> getGamesData(){
        return gameData;
    }

    public void findGamesData() throws FileNotFoundException {
        ArrayList<String> path = new ArrayList<>();
        Path dir = FileSystems.getDefault().getPath("../games"); // placer les jeux dans un dossier games
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {  // on liste les fichiers et dossiers
            for (Path file: stream) {
                String a = file.getFileName().toString();
                path.add(a);
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }

        ArrayList<String> images;
        for (String p : path) {
            // trouver les noms et descriptions
            File file = new File("../games/" +p + "/description.txt");
            String description = "";
            String name = "";
            if (file.exists()) {
                Scanner reader = new Scanner(file);
                name = reader.nextLine();
                String u = reader.nextLine();
                description = u + "\n";
                while (reader.hasNextLine()) {
                    u = reader.nextLine();
                    description += u + "\n";
                }
            }
            // trouver les images associés
            images = new ArrayList<>();
            dir = FileSystems.getDefault().getPath("../games/"+p);
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {  // on liste les fichiers et dossiers
                for (Path f: stream) {
                    String a = f.getFileName().toString();
                    if (a.lastIndexOf(".") != -1) {
                        String ext = a.substring(a.lastIndexOf(".")); //extension du fichier
                        if (ext.equals(".jpg") || ext.equals(".png")) {
                            images.add(f.toString());
                        }
                    }
                }
            } catch (IOException | DirectoryIteratorException x) {
                System.err.println(x);
            }
            gameData.add(new GameData(name, description,"../games/"+p,images)); // on sauvegarde tout dans un GameData
        }
    }
}