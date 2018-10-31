package pl.gb.codecool.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Cell> board;
    List<Cell> tempBoard;

    private int COLUMNS;
    private int ROWS;

    public Board(int COLUMNS, int ROWS) {
        this.COLUMNS = COLUMNS;
        this.ROWS = ROWS;
        board = generateEmptyBoard();
        tempBoard = copyBoard(board);
    }

    private List<Cell> generateExampleBoard() {
        List<Cell> board = new ArrayList<>();
        for (int i = 0; i < getROWS(); i++) {
            for (int j = 0; j < getCOLUMNS(); j++) {
                Cell cell = new Cell();
                cell.setId(i + "" + j);
                if (cell.getId().equals("11") || cell.getId().equals("12") ||
                        cell.getId().equals("21") || cell.getId().equals("22") ||
                        cell.getId().equals("33") || cell.getId().equals("34") ||
                        cell.getId().equals("43") || cell.getId().equals("44")) {
                    cell.setState(true);
                }
                board.add(cell);
            }
        }
        return board;
    }

    private List<Cell> generateExample3Board() {
        List<Cell> board = new ArrayList<>();
        for (int i = 0; i < getROWS(); i++) {
            for (int j = 0; j < getCOLUMNS(); j++) {
                Cell cell = new Cell();
                cell.setId(i + "" + j);
                if (cell.getId().equals("22") || cell.getId().equals("23") || cell.getId().equals("24") ||
                        cell.getId().equals("31") || cell.getId().equals("32") || cell.getId().equals("33")) {
                    cell.setState(true);
                }
                board.add(cell);
            }
        }
        return board;
    }

    private List<Cell> generateExample2Board() {
        List<Cell> board = new ArrayList<>();
        for (int i = 0; i < getROWS(); i++) {
            for (int j = 0; j < getCOLUMNS(); j++) {
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

    private List<Cell> generateEmptyBoard() {
        List<Cell> board = new ArrayList<>();
        for (int i = 0; i < getROWS(); i++) {
            for (int j = 0; j < getCOLUMNS(); j++) {
                Cell cell = new Cell();
                cell.setId(i + "" + j);
                board.add(cell);
            }
        }
        return board;
    }

    public List<Cell> copyBoard(List<Cell> board) {
        List<Cell> newBoard = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            Cell cell = new Cell();
            cell.setId(board.get(i).getId());
            cell.setState(board.get(i).isState());
            newBoard.add(cell);
        }
        return newBoard;
    }


    public int getCOLUMNS() {
        return COLUMNS;
    }

    public int getROWS() {
        return ROWS;
    }

    public List<Cell> getBoard() {
        return board;
    }

    public List<Cell> getTempBoard() {
        return tempBoard;
    }
}
