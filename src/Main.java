import javafx.application.Application;
import javafx.stage.Stage;
import view.Launcher;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Launcher launcher = new Launcher();
        launcher.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
