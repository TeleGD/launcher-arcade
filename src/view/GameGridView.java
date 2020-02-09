package view;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class GameGridView extends GridPane {

    public GameGridView(){
        int number = Math.min(9, Launcher.getInstance().getGamesData().size()); // on dépasse l'space d'affichage
        for (int i = 0; i<number; i++){
            GameButton gameButton = new GameButton(Launcher.getInstance().getGamesData().get(i),this);
            this.setMargin(gameButton,new Insets(5,5,5,5));
            this.add(gameButton, i%3, i/3);
        }
    }

    public double getSizeX(){
        return Launcher.getInstance().getSizeX() - 400 ;
    }

    public double getSizeY(){
        return Launcher.getInstance().getSizeY() - 80 ;
    }
}
