package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class GameGridView extends GridPane {
    private int selectedButton;

    public GameGridView(){
        selectedButton = 0;
        int number = Math.min(9, Launcher.getInstance().getGamesData().size()); // on dépasse l'espace d'affichage
        for (int i = 0; i<number; i++){
            GameButton gameButton = new GameButton(Launcher.getInstance().getGamesData().get(i),this);
            this.setMargin(gameButton,new Insets(5,5,5,5));
            this.add(gameButton, i%3, i/3);
        }
    }

    public double getSizeX(){
        return Launcher.getInstance().getSizeX() - Launcher.getInstance().getSizeX()/4;
    }

    public double getSizeY(){
        return Launcher.getInstance().getSizeY() - 120 ;
    }

    public void update(int select){
        selectedButton = select;
        int i = 0;
        for (Node button : getChildren()){
            ((GameButton)button).update(i==selectedButton);
            i++;
        }
    }
    public int buttonSelected(){
        return selectedButton;
    }
}

