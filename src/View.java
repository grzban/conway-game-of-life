import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View extends Application {

    @Override
    public void start(Stage primaryStage) {
        int WIDTH = 300;
        int HEIGHT = 350;

        primaryStage.setTitle("Conway's Game of Life");

        BorderPane borderPane = new BorderPane();

        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(0, 0, 0, 0));


        int rows = 15;
        int columns = 15;

        for (int row = 0; row < rows; row++){

            for (int column = 0; column < columns; column++){
                Rectangle cell = new Rectangle();
                cell.setStroke(Color.BLACK);
                cell.setHeight(15);
                cell.setWidth(15);
                cell.setOnMousePressed(event -> {
                    if(cell.getFill().equals(Color.GREEN)) {
                        cell.setFill(Color.RED);
                    } else {
                        cell.setFill(Color.GREEN);
                    }
                });
                cell.setFill(Color.RED);

                grid.add(cell, column, row);
            }

        }
        Button startButton = new Button("Start");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(startButton);
        borderPane.setBottom(hBox);
        borderPane.setCenter(grid);
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        primaryStage.setScene(new Scene(borderPane, WIDTH, HEIGHT));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
