package pl.gb.codecool.model;

import java.util.List;

public class Game {
    private int ROWS;
    private int COLUMNS;

    private List<Cell> gameBoard;
    private List<Cell> tempGameBoard;
    private Board board;

    public Game(int COLUMNS, int ROWS) {
        this.COLUMNS = COLUMNS;
        this.ROWS = ROWS;
        newGame();
    }

    public void newGame() {
        board = new Board(COLUMNS, ROWS);
        gameBoard = board.getBoard();
        tempGameBoard = board.getTempBoard();
    }

    public void move() {
        for (int index = 0; index < gameBoard.size(); index++) {
            changeCellStatus(index);
        }
        gameBoard = board.copyBoard(tempGameBoard);
        tempGameBoard = board.copyBoard(gameBoard);
    }

    private boolean isNeighbourAlive(int neighbourIndex) {
        int row = rowNumber(neighbourIndex);
        if (row > 0) {
            return gameBoard.get(neighbourIndex).isState();
        }
        return false;
    }

    private int rowNumber(int index) {
        for (int i = 0; i < ROWS; i++) {
            int start = i * COLUMNS;
            int end = start + COLUMNS - 1;
            System.out.println(start + " " + end);
            if (index >= start && index <= end) {
                return i;
            }
        }
        return -1;
    }

    private boolean isUpNeighbourAlive(int index) {
        int upNeighbourIndex = index - COLUMNS;
        return isNeighbourAlive(upNeighbourIndex);
    }

    private boolean isUpLeftNeighbourAlive(int index) {
        int upLeftNeighbourIndex = index - COLUMNS - 1;
        return isNeighbourAlive(upLeftNeighbourIndex);
    }

    private boolean isUpRightNeighbourAlive(int index) {
        int upRightNeighbourIndex = index - COLUMNS + 1;
        return isNeighbourAlive(upRightNeighbourIndex);
    }

    private boolean isDownNeighbourAlive(int index) {
        int downNeighbourIndex = index + COLUMNS;
        return isNeighbourAlive(downNeighbourIndex);
    }

    private boolean isDownLeftNeighbourAlive(int index) {
        int downLeftNeighbourIndex = index + COLUMNS - 1;
        return isNeighbourAlive(downLeftNeighbourIndex);
    }

    private boolean isDownRightNeighbourAlive(int index) {
        int downRightNeighbourIndex = index + COLUMNS + 1;
        return isNeighbourAlive(downRightNeighbourIndex);
    }

    private boolean isLeftNeighbourAlive(int index) {
        int leftNeighbourIndex = index - 1;
        return isNeighbourAlive(leftNeighbourIndex);
    }

    private boolean isRightNeighbourAlive(int index) {
        int rightNeighbourIndex = index + 1;
        return isNeighbourAlive(rightNeighbourIndex);
    }

    private int aliveNeighbours(boolean isAlive, int aliveNeighbours) {
        if (isAlive) {
            aliveNeighbours++;
        }
        return aliveNeighbours;
    }

    public int howManyNeighboursIsAlive(int index) {

        int aliveNeighbours = 0;

        aliveNeighbours = aliveNeighbours(isUpLeftNeighbourAlive(index), aliveNeighbours);
        aliveNeighbours = aliveNeighbours(isUpNeighbourAlive(index), aliveNeighbours);
        aliveNeighbours = aliveNeighbours(isUpRightNeighbourAlive(index), aliveNeighbours);

        aliveNeighbours = aliveNeighbours(isRightNeighbourAlive(index), aliveNeighbours);
        aliveNeighbours = aliveNeighbours(isLeftNeighbourAlive(index), aliveNeighbours);

        aliveNeighbours = aliveNeighbours(isDownLeftNeighbourAlive(index), aliveNeighbours);
        aliveNeighbours = aliveNeighbours(isDownNeighbourAlive(index), aliveNeighbours);
        aliveNeighbours = aliveNeighbours(isDownRightNeighbourAlive(index), aliveNeighbours);

        return aliveNeighbours;
    }

    public void changeCellState(int index, boolean state) {
        gameBoard.get(index).setState(state);
    }

    public void changeCellStatus(int index) {
        int aliveNeighbours = howManyNeighboursIsAlive(index);
        if (gameBoard.get(index).isState()) {
            //Any live cell with fewer than two live neighbors dies, as if by underpopulation.
            if (aliveNeighbours < 2) {
                tempGameBoard.get(index).setState(false);
            }
            //Any live cell with two or three live neighbors lives on to the next generation.
            else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                tempGameBoard.get(index).setState(true);
            }//Any live cell with more than three live neighbors dies, as if by overpopulation.
            else if (aliveNeighbours > 3) {
                tempGameBoard.get(index).setState(false);
            }
        }//Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
        else {
            if (aliveNeighbours == 3) {
                tempGameBoard.get(index).setState(true);
            }
        }
    }

    public List<Cell> getGameBoard() {
        return gameBoard;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
