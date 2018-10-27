package pl.gb.codecool.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Cell> board;

    public Board(int width, int heigth) {
        this.board = generateBoard();
    }

    private List<Cell> generateBoard(){
        List<Cell> board = new ArrayList<>();

        return board;
    }
}
