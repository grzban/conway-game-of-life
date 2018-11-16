package pl.gb.codecool.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final int WIDTH = 650;
        final int HEIGHT = 600;
        Parent root = FXMLLoader.load(getClass().getResource("game_of_life.fxml"));
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
