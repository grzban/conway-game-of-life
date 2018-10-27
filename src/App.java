import pl.gb.codecool.model.Cell;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Cell> board = new ArrayList<>();

        int width = 5;
        int height = 5;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = new Cell();
                cell.setId(i + "" + j);
                if (cell.getId().equals("12") || cell.getId().equals("22") || cell.getId().equals("32")) {
                    cell.setState(true);
                }
                board.add(cell);
            }
        }
        List<Cell> temp_board = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            Cell cell = new Cell();
            cell.setId(board.get(i).getId());
            cell.setState(board.get(i).isState());
            temp_board.add(cell);
        }

        int index = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int aliveNeighbours = 0;

                int upNeighbourIndex = index - width;
                int upLeftNeighbourIndex = index - width - 1;
                int upRightNeighbourIndex = index - width + 1;

                int downNeighbourIndex = index + width;
                int downLeftNeighbourIndex = index + width - 1;
                int downRightNeighbourIndex = index + width + 1;

                int leftNeighbourIndex = index - 1;
                int rightNeighbourIndex = index + 1;

                System.out.println();
                System.out.println("Neighbours for " + index);

                //UP LEFT
                if ((upLeftNeighbourIndex >= (i - 1) * width && upLeftNeighbourIndex <= i * width - 1) && upLeftNeighbourIndex >= 0) {
                    System.out.println("UP LEFT " + upLeftNeighbourIndex + " is " + board.get(upLeftNeighbourIndex).isState());
                    if (board.get(upLeftNeighbourIndex).isState()) {
                        aliveNeighbours++;
                    }
                }
                //UP
                if ((upNeighbourIndex >= (i - 1) * width && upNeighbourIndex <= i * width - 1) && upNeighbourIndex >= 0) {
                    System.out.println("UP " + upNeighbourIndex + " is " + board.get(upNeighbourIndex).isState());
                    if (board.get(upNeighbourIndex).isState()) {
                        aliveNeighbours++;
                    }
                }
                //UP RIGHT
                if ((upRightNeighbourIndex >= (i - 1) * width && upRightNeighbourIndex <= i * width - 1) && upRightNeighbourIndex >= 0) {
                    System.out.println("UP RIGHT " + upRightNeighbourIndex + " is " + board.get(upRightNeighbourIndex).isState());
                    if (board.get(upRightNeighbourIndex).isState()) {
                        aliveNeighbours++;
                    }
                }

                //LEFT
                if ((leftNeighbourIndex >= i * width && leftNeighbourIndex <= (i + 1) * width - 1) && leftNeighbourIndex >= 0) {
                    System.out.println("LEFT " + leftNeighbourIndex + " is " + board.get(leftNeighbourIndex).isState());
                    if (board.get(leftNeighbourIndex).isState()) {
                        aliveNeighbours++;
                    }
                }

                //RIGHT

                if ((rightNeighbourIndex >= i * width && rightNeighbourIndex <= (i + 1) * width - 1) && rightNeighbourIndex <= board.size()) {
                    System.out.println("RIGHT " + rightNeighbourIndex + " is " + board.get(rightNeighbourIndex).isState());
                    if (board.get(rightNeighbourIndex).isState()) {
                        aliveNeighbours++;
                    }
                }

                //DOWN LEFT
                if ((downLeftNeighbourIndex >= (i + 1) * width && downLeftNeighbourIndex <= (i + 2) * width - 1) && downLeftNeighbourIndex < board.size()) {
                    System.out.println("DOWN LEFT " + downLeftNeighbourIndex + " is " + board.get(downLeftNeighbourIndex).isState());
                    if (board.get(downLeftNeighbourIndex).isState()) {
                        aliveNeighbours++;
                    }
                }
                //DOWN
                if ((downNeighbourIndex >= (i + 1) * width && downNeighbourIndex <= (i + 2) * width - 1) && downNeighbourIndex < board.size()) {
                    System.out.println("DOWN " + downNeighbourIndex + " is " + board.get(downNeighbourIndex).isState());
                    if (board.get(downNeighbourIndex).isState()) {
                        aliveNeighbours++;
                    }
                }
                //DOWN RIGHT
                if ((downRightNeighbourIndex >= (i + 1) * width && downRightNeighbourIndex <= (i + 2) * width - 1) && downRightNeighbourIndex < board.size()) {
                    System.out.println("DOWN RIGHT " + downRightNeighbourIndex + " is " + board.get(downRightNeighbourIndex).isState());
                    if (board.get(downRightNeighbourIndex).isState()) {
                        aliveNeighbours++;
                    }
                }

                System.out.println(aliveNeighbours);

                if (board.get(index).isState()) {
                    System.out.println("I'm alive cell");
                    //Any live cell with fewer than two live neighbors dies, as if by underpopulation.
                    if (aliveNeighbours < 2) {
                        System.out.println("I have " + aliveNeighbours + " neighbour(s). I have to die.");
                        temp_board.get(index).setState(false);
                    }
                    //Any live cell with two or three live neighbors lives on to the next generation.
                    else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                        System.out.println("I have " + aliveNeighbours + " neighbour(s). I will be alive");
                        temp_board.get(index).setState(true);
                    }//Any live cell with more than three live neighbors dies, as if by overpopulation.
                    else if (aliveNeighbours > 3) {
                        System.out.println("I have " + aliveNeighbours + " neighbour(s). I have to die, because overpopulation.");
                        temp_board.get(index).setState(false);
                    }
                }//Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
                else {
                    System.out.println("I'm dead cell");
                    if (aliveNeighbours == 3 ) {
                        System.out.println("I have " + aliveNeighbours + " neighbour(s). I will be alive.");
                        temp_board.get(index).setState(true);
                    }
                }

                index++;
            }
        }

        System.out.println(board);
        System.out.println(temp_board);
        System.out.println(board == temp_board);
    }
}
