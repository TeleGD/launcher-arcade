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

    public void findGamesData() throws FileNotFoundException {
        ArrayList<String> path = new ArrayList<>();
        Path dir = FileSystems.getDefault().getPath("../");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {  // on liste les fichiers et dossiers
            for (Path file: stream) {
                String a = file.getFileName().toString();
                if (!a.equals( "tgd-launcher-arcade")) {
                    path.add(a);
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }

        ArrayList<String> images;
        for (String p : path) {
            // trouver les noms et descriptions
            File file = new File("../" +p + "/description.txt");
            Scanner reader = new Scanner(file);
            String name = reader.nextLine();
            String description;
            String u = reader.nextLine();
            description = u+"\n";
            while (reader.hasNextLine()){
                u = reader.nextLine();
                description+=u+"\n";
            }

            // trouver les images associés
            images = new ArrayList<>();
            dir = FileSystems.getDefault().getPath("../"+p);
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {  // on liste les fichiers et dossiers
                for (Path f: stream) {
                    String a = f.getFileName().toString();
                    if (a.lastIndexOf(".") != -1) {
                        String ext = a.substring(a.lastIndexOf(".")); //extension du fichier
                        if (ext.equals(".jpg") || ext.equals(".png")) {
                            images.add(f.toString());
                            System.out.println(f);
                        }
                    }
                }
            } catch (IOException | DirectoryIteratorException x) {
                System.err.println(x);
            }
            System.out.println(name);
            System.out.println(description);
            System.out.println(p);
            System.out.println(images);
            gameData.add(new GameData(name, description,p,images)); // on sauvegarde tout dans un GameData
        }
    }

    public ArrayList<GameData> getGamesData(){
        return gameData;
    }
}