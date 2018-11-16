package pl.gb.codecool;

import pl.gb.codecool.model.Game;

public class Test {
    int rows = 5;
    int columns = 5;

    public Test() {
        Game game = new Game(rows, columns);
        System.out.println(game.getGameBoard());
        int i = 0;
        while (i < rows * columns) {
            System.out.print(game.rowNumber(i));
            i++;
        }

        showBoard(game);
        game.move();
        showBoard(game);
        game.move();
        showBoard(game);
    }

    public static void main(String[] args) {
        new Test();

    }

    private void showBoard(Game game) {
        System.out.println();
        int index = 0;

        int i = 0;
        while (i < rows) {
            int j = 0;
            while (j < columns) {
                System.out.print("index = " + index + "\t" + game.getGameBoard().get(index).isState() + "\t" + game.howManyNeighboursIsAlive(index) + "\t");
                j++;
                index++;
            }
            System.out.println();
            i++;
        }
        System.out.println();
    }
}
