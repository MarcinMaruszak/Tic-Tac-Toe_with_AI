package tictactoe.Player;

import tictactoe.Board;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class AIMedium extends Player {

    public AIMedium(char mark) {
        super(mark);
    }

    @Override
    public void makeMove(Board board, Scanner scanner) {
        System.out.println("Making move level \"medium\"");
        if (!madeWinMove(board)) {
            if (!madeBlockMove(board)) {
                makeRndMove(board);
            }

        }
    }

    private boolean madeBlockMove(Board board) {
        for (int row = 0; row < board.getBoard().length; row++) {
            if (canBlock(board.getBoard()[row])) {
                int empty = getEmptyCell(board.getBoard()[row]);
                board.markCoordinates(new int[]{row + 1, empty + 1}, getMark());
                return true;
            }
        }
        for (int i = 0; i < board.getBoard().length; i++) {
            Character[] col = board.getCol(i);
            if (canBlock(col)) {
                int empty = getEmptyCell(col);
                board.markCoordinates(new int[]{empty + 1, i + 1}, getMark());
                return true;
            }
        }
        Character[][] diagonals = board.getDiagonals();
        if (canBlock(diagonals[0])) {
            int empty = getEmptyCell(diagonals[0]);
            board.markCoordinates(new int[]{empty + 1, empty + 1}, getMark());
            return true;
        }
        if (canBlock(diagonals[1])) {
            int empty = getEmptyCell(diagonals[1]);
            board.markCoordinates(new int[]{3 - empty, empty + 1}, getMark());
            return true;
        }

        return false;
    }

    private boolean canBlock(Character[] row) {
        return Arrays.stream(row).filter(x -> x == ' ').count() == 1 &&
                Arrays.stream(row).filter(x -> x != ' ' && x != getMark()).count() == 2;

    }

    private boolean madeWinMove(Board board) {
        for (int row = 0; row < board.getBoard().length; row++) {
            if (canMakeLastMove(board.getBoard()[row])) {
                int empty = getEmptyCell(board.getBoard()[row]);
                board.markCoordinates(new int[]{row + 1, empty + 1}, getMark());
                return true;
            }
        }
        for (int i = 0; i < board.getBoard().length; i++) {
            Character[] col = board.getCol(i);
            if (canMakeLastMove(col)) {
                int empty = getEmptyCell(col);
                board.markCoordinates(new int[]{empty + 1, i + 1}, getMark());
                return true;
            }
        }
        Character[][] diagonals = board.getDiagonals();
        if (canMakeLastMove(diagonals[0])) {
            int empty = getEmptyCell(diagonals[0]);
            board.markCoordinates(new int[]{empty + 1, empty + 1}, getMark());
            return true;
        }
        if (canMakeLastMove(diagonals[1])) {
            int empty = getEmptyCell(diagonals[1]);
            board.markCoordinates(new int[]{3 - empty, empty + 1}, getMark());
            return true;
        }
        return false;
    }

    private boolean canMakeLastMove(Character[] row) {
        return Arrays.stream(row).filter(x -> x == getMark()).count() == 2 &&
                Arrays.stream(row).filter(x -> x == ' ').count() == 1;
    }

    private int getEmptyCell(Character[] characters) {
        for (int i = 0; i < characters.length; i++) {
            if (characters[i] == ' ') {
                return i;
            }
        }
        return -1;
    }

    public void makeRndMove(Board board) {
        int row = new Random().nextInt(3) + 1;
        int col = new Random().nextInt(3) + 1;
        while (board.occupied(new int[]{row, col})) {
            row = new Random().nextInt(3) + 1;
            col = new Random().nextInt(3) + 1;
        }
        board.markCoordinates(new int[]{row, col}, getMark());
    }
}
