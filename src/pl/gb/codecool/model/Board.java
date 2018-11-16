package pl.gb.codecool.model;

import java.util.ArrayList;
import java.util.List;

class Board {
    private List<Cell> board;

    private int COLUMNS;
    private int ROWS;

    Board(int COLUMNS, int ROWS) {
        this.COLUMNS = COLUMNS;
        this.ROWS = ROWS;
        board = generateEmptyBoard();
    }

    private List<Cell> generateEmptyBoard() {
        List<Cell> board = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Cell cell = new Cell();
                cell.setId(i + "" + j);
                board.add(cell);
            }
        }
        return board;
    }


    List<Cell> copyBoard(List<Cell> board) {
        List<Cell> newBoard = new ArrayList<>();
        for (Cell cellToCopy : board) {
            Cell cell = new Cell();
            cell.setState(cellToCopy.isState());
            cell.setId(cellToCopy.getId());
            newBoard.add(cell);
        }
        return newBoard;
    }

    List<Cell> getBoard() {
        return board;
    }
}
