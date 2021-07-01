package tictactoe.Player;

import tictactoe.Board;
import tictactoe.Player.Player;

import java.util.Arrays;
import java.util.Scanner;

public class UserPlayer extends Player {

    public UserPlayer(char mark) {
        super(mark);
    }

    @Override
    public void makeMove(Board board, Scanner scanner) {
        int[] coordinates = getCoordinates(board, scanner);
        board.markCoordinates(coordinates, getMark());
    }

    private int[] getCoordinates(Board board, Scanner scanner) {
        System.out.print("Enter the coordinates: ");
        int[] coordinates;
        try {
            coordinates = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            if (coordinates[0] > 3 || coordinates[0] < 1 || coordinates[1] > 3 || coordinates[1] < 1 || coordinates.length > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                return getCoordinates(board, scanner);
            } else if (board.occupied(coordinates)) {
                System.out.println("This cell is occupied! Choose another one!");
                return getCoordinates(board, scanner);
            }
            return coordinates;

        } catch (Exception e) {
            System.out.println("You should enter numbers!");
            return getCoordinates(board, scanner);

        }
    }

}
