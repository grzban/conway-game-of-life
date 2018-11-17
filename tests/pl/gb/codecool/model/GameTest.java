package pl.gb.codecool.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class GameTest {

    private int rows;
    private int columns;
    private List<Cell> gameBoard;
    private Game game;

    @BeforeEach
    void setUp() {
        rows = 5;
        columns = 5;
        gameBoard = generateExampleBoard();
        game = new Game(rows, columns);
    }

    private List<Cell> generateExampleBoard() {
        List<Cell> board = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = new Cell();
                cell.setId(i + "" + j);
                if (cell.getId().equals("12") || cell.getId().equals("22") || cell.getId().equals("32")) {
                    cell.setState(true);
                }
                board.add(cell);
            }
        }
        return board;
    }

    private List<Cell> generateComparisonBoard() {
        List<Cell> board = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = new Cell();
                cell.setId(i + "" + j);
                if (cell.getId().equals("21") || cell.getId().equals("22") || cell.getId().equals("23")) {
                    cell.setState(true);
                }
                board.add(cell);
            }
        }
        return board;
    }

    @Test
    void move() {
        List<Cell> comparisonBoard = generateComparisonBoard();
        game.setGameBoard(gameBoard);
        game.move();

        int i = 0;
        int gameBoardSize = gameBoard.size();
        while (i < gameBoardSize) {
            Assertions.assertEquals(comparisonBoard.get(i).isState(), game.getGameBoard().get(i).isState());
            i++;
        }
    }

    @Test
    void rowNumber() {
        int i = 0;
        int gameBoardSize = gameBoard.size();
        int j = -1;
        while (i < gameBoardSize) {
            if (((i + rows) % rows) == 0)
                j++;
            Assertions.assertEquals(j, game.rowNumber(i));
            i++;
        }
    }

    @Test
    void howManyNeighboursIsAlive() {
        game.setGameBoard(gameBoard);
        int i = 0;
        int gameBoardSize = gameBoard.size();
        int [] aliveNeighbours = {0, 1, 1, 1, 0, 0, 2, 1, 2, 0, 0, 3, 2, 3, 0, 0, 2, 1, 2, 0, 0, 1, 1, 1, 0};

        while (i < gameBoardSize) {
            Assertions.assertEquals(aliveNeighbours[i], game.howManyNeighboursIsAlive(i));
            i++;
        }
    }

    @Test
    void changeCellState() {
    }
}