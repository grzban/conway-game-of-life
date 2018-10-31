package pl.gb.codecool.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import pl.gb.codecool.model.Game;

public class View extends Application {

    private int rows;
    private int columns;

    private GridPane showInitialBoard(Game game) {
        GridPane grid = new GridPane();

        int index = 0;
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(0, 0, 0, 0));

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Rectangle cell = new Rectangle();
                cell.setFill(Color.WHITE);
                cell.setStroke(Color.BLACK);
                cell.setHeight(15);
                cell.setWidth(15);
                int finalIndex = index;
                cell.setOnMouseClicked(e -> {
                    if (cell.getFill().equals(Color.WHITE)) {
                        game.changeCellState(finalIndex, true);
                        cell.setFill(Color.BLACK);
                    } else {
                        game.changeCellState(finalIndex, false);
                        cell.setFill(Color.WHITE);
                    }
                });

                grid.add(cell, column, row);
                index++;
            }
        }
        return grid;
    }

    private GridPane showBoard(Game game) {
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(0, 0, 0, 0));

        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Rectangle cell = new Rectangle();
                cell.setStroke(Color.BLACK);
                cell.setHeight(15);
                cell.setWidth(15);
                if (game.getGameBoard().get(index).isState()) {
                    cell.setFill(Color.BLACK);
                } else {
                    cell.setFill(Color.WHITE);
                }

                grid.add(cell, column, row);
                index++;
            }
        }
        return grid;
    }

    @Override
    public void start(Stage primaryStage) {
        setRows(25);
        setColumns(25);
        int WIDTH = 600;
        int HEIGHT = 600;

        primaryStage.setTitle("Conway's Game of Life");
        Game game = new Game(getColumns(), getRows());

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, WIDTH, HEIGHT);

        Label movementsLabel = new Label("Movements");
        Label movementsCountLabel = new Label("0");

        movementsLabel.setPadding(new Insets(20, 20, 20, 20));
        movementsCountLabel.setPadding(new Insets(20, 20, 20, 20));
        HBox hBoxUp = new HBox();
        hBoxUp.getChildren().add(movementsLabel);
        hBoxUp.getChildren().add(movementsCountLabel);
        hBoxUp.setAlignment(Pos.TOP_LEFT);

        HBox hBoxDown = new HBox();
        hBoxDown.setAlignment(Pos.CENTER);
        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button newGameButton = new Button("New Game");
        startButton.setDisable(false);
        stopButton.setDisable(true);
        newGameButton.setDisable(true);

        hBoxDown.getChildren().add(startButton);
        hBoxDown.getChildren().add(stopButton);
        hBoxDown.getChildren().add(newGameButton);

        borderPane.setTop(hBoxUp);
        borderPane.setBottom(hBoxDown);
        borderPane.setCenter(showInitialBoard(game));
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        primaryStage.setScene(scene);


        Timeline timeline = new Timeline();

        int move = 0;
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(200), event -> {
                    game.move();
                    if (game.stopAnimation()) {
                        timeline.stop();
                        stopButton.setDisable(true);
                        newGameButton.setDisable(false);
                    }
                    game.prepareBoards();
                    borderPane.setCenter(showBoard(game));
                    movementsCountLabel.setText((Integer.parseInt(movementsCountLabel.getText()) + 1)  + "");

                    hBoxUp.getChildren().clear();
                    hBoxUp.getChildren().add(movementsLabel);
                    hBoxUp.getChildren().add(movementsCountLabel);
                    borderPane.setTop(hBoxUp);
                    primaryStage.getScene().setRoot(borderPane);
                }));

        timeline.setCycleCount(Animation.INDEFINITE);

        startButton.setOnAction(event -> {
            startButton.setDisable(true);
            stopButton.setDisable(false);
            timeline.play();
        });

        stopButton.setOnAction(event -> {
            stopButton.setDisable(true);
            newGameButton.setDisable(false);
            timeline.stop();
        });

        newGameButton.setOnAction(event -> {
            movementsCountLabel.setText("0");
            hBoxUp.getChildren().clear();
            hBoxUp.getChildren().add(movementsLabel);
            hBoxUp.getChildren().add(movementsCountLabel);
            borderPane.setTop(hBoxUp);
            game.newGame();
            borderPane.setCenter(showInitialBoard(game));
            primaryStage.getScene().setRoot(borderPane);
            stopButton.setDisable(true);
            startButton.setDisable(false);
            newGameButton.setDisable(true);

        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
