package view;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class GameGridView extends GridPane {

    public GameGridView(){
        int number = 9;
        for (int i = 0; i<number; i++){
            GameButton gameButton = new GameButton("test"+i,i%2==0?"res/test/image1.jpg":"res/test/image2.jpg",this);
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
