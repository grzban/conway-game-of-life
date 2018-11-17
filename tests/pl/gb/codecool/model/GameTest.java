package pl.gb.codecool.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void move() {
//        [00 false, 01 false, 02 false, 03 false, 04 false, 10 false, 11 false, 12 true, 13 false, 14 false, 20 false, 21 false, 22 true, 23 false, 24 false, 30 false, 31 false, 32 true, 33 false, 34 false, 40 false, 41 false, 42 false, 43 false, 44 false]
        System.out.println(gameBoard);
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
    }

    @Test
    void changeCellState() {
    }

    @Test
    void isGameStatus() {
    }
}