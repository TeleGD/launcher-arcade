import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.GameButton;
import view.Launcher;

public class Main extends Application {
    public static Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Launcher launcher = new Launcher();
        launcher.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
