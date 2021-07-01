package tictactoe.Player;

import tictactoe.Board;

import java.util.Random;
import java.util.Scanner;

public class AIEasy extends Player{

    public AIEasy(char mark) {
        super(mark);
    }

    public void makeMove(Board board, Scanner scanner) {
        System.out.println("Making move level \"easy\"");
        int row = new Random().nextInt(3)+1;
        int col = new Random().nextInt(3)+1;
        while (board.occupied(new int []{row, col})){
            row = new Random().nextInt(3)+1;
            col = new Random().nextInt(3)+1;
        }
        board.markCoordinates(new int [] {row, col} , getMark());
    }
}
