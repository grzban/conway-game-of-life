package pl.gb.codecool.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import pl.gb.codecool.model.Game;

public class GameOfLifeController {
    private int rows = 50;
    private int columns = 50;

    private Game game;
    private Timeline timeline;
    private GridPane grid;

    @FXML
    private Pane board_container;

    @FXML
    private Button start_button;

    @FXML
    private Button pause_button;

    @FXML
    private Button stop_button;

    @FXML
    private Button new_game_button;

    @FXML
    private Slider speed_slider;

    @FXML
    private Label moves_count;

    @FXML
    private Label speed_label;

    public void initialize() {
        grid = new GridPane();
        board_container.getChildren().add(grid);
        newGame();
    }


    @FXML
    private void startButtonAction() {

        timeline = new Timeline();
        Duration speed = new Duration(100000 / speed_slider.getValue());
        KeyFrame keyFrame = new KeyFrame(speed, (ev) -> {
            if (game.isGameStatus()) {
                game.move();
                showBoard(game);
                moves_count.setText((Integer.parseInt(moves_count.getText()) + 1) + "");
                start_button.setDisable(true);
                pause_button.setDisable(false);
                stop_button.setDisable(false);
                new_game_button.setDisable(true);
                speed_slider.setDisable(true);
            } else {
                stopGame();
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

    }

    private void newGame() {
        game = new Game(rows, columns);
        moves_count.setText("0");
        speed_slider.setValue(100);
        start_button.setDisable(false);
        stop_button.setDisable(true);
        pause_button.setDisable(true);
        new_game_button.setDisable(true);
        speed_slider.setDisable(false);
        speed_label.setText((int) speed_slider.getValue() + "");
        showBoard(game);
    }


    @FXML
    private void pauseButtonAction() {
        if (pause_button.getText().equals("PAUSE")) {
            pause_button.setText("RESUME");
            timeline.pause();
        } else {
            pause_button.setText("PAUSE");
            timeline.play();
        }

        start_button.setDisable(true);
        stop_button.setDisable(false);
        pause_button.setDisable(false);
        new_game_button.setDisable(true);
        speed_slider.setDisable(true);
    }

    @FXML
    private void stopButtonAction() {
        stopGame();
    }

    private void stopGame() {
        pause_button.setText("PAUSE");
        new_game_button.setDisable(false);
        stop_button.setDisable(true);
        start_button.setDisable(true);
        pause_button.setDisable(true);
        speed_slider.setDisable(true);
        timeline.stop();
    }

    @FXML
    public void speedSliderAction() {
        speed_label.setText((int) speed_slider.getValue() + "");
    }

    @FXML
    private void newGameButtonAction() {
        newGame();
    }

    private void showBoard(Game game) {
        grid.getChildren().clear();
        int index = 0;
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(0, 0, 0, 0));

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Rectangle cell = new Rectangle();
                cell.setFill(Color.WHITE);
                cell.setStroke(Color.BLACK);
                int cellHeight = 10;
                cell.setHeight(cellHeight);
                int cellWeight = 10;
                cell.setWidth(cellWeight);
                int finalIndex = index;
                if (start_button.isDisable()) {
                    if (game.getGameBoard().get(index).isState()) {
                        cell.setFill(Color.BLACK);
                    } else {
                        cell.setFill(Color.WHITE);
                    }
                } else {
                    cell.setOnMouseClicked(e -> {
                        if (cell.getFill().equals(Color.WHITE)) {
                            game.changeCellState(finalIndex, true);
                            cell.setFill(Color.BLACK);
                        } else {
                            game.changeCellState(finalIndex, false);
                            cell.setFill(Color.WHITE);
                        }
                    });
                }
                grid.add(cell, column, row);
                index++;
            }
        }
    }
}
