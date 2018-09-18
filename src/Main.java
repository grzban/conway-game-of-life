import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private int WIDTH = 500;
    private int HEIGHT = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("Game of life");
    }
}
