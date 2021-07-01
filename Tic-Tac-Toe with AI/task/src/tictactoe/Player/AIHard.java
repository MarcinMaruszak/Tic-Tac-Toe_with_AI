package tictactoe.Player;

import tictactoe.Board;
import tictactoe.GameStatus;

import java.util.Arrays;
import java.util.Scanner;

public class AIHard extends Player {

    public AIHard(char mark) {
        super(mark);
    }

    @Override
    public void makeMove(Board board, Scanner scanner) {
        int[] move = findBestMove(board);
        board.markCoordinates(move, getMark());
    }

    private int[] findBestMove(Board board) {
        int maxVal = -10000;
        int rowVal = -1;
        int colVal = -1;
        for (int row = 0; row < board.getBoard().length; row++) {
            for (int col = 0; col < board.getBoard().length; col++) {
                int[] coordinates = new int[]{row + 1, col + 1};
                if (!board.occupied(coordinates)) { //empty
                    board.markCoordinates(coordinates, getMark());
                    int val = minMax(board, 0,false);
                    board.markCoordinates(coordinates, ' ');
                    if (val > maxVal) {
                        maxVal = val;
                        rowVal = row + 1;
                        colVal = col + 1;
                    }
                }
            }
        }
        return new int[]{rowVal, colVal};
    }

    private int minMax(Board board, int depth,  boolean isMaxPlayer) {
        if (board.checkStatusOfGame().equals(GameStatus.DRAW)) {
            return 0;
        }
        if (board.checkStatusOfGame().equals(GameStatus.XWin)) {
            if (getMark() == 'X') {
                return 10 - depth;
            } else {
                return -10 + depth;
            }
        }
        if (board.checkStatusOfGame().equals(GameStatus.OWin)) {
            if (getMark() == 'O') {
                return 10 - depth;
            } else {
                return -10 + depth;
            }
        }

        int best;
        if (isMaxPlayer) {
            best = -1000;
            for (int row = 0; row < board.getBoard().length; row++) {
                for (int col = 0; col < board.getBoard().length; col++) {
                    int[] coordinates = new int[]{row + 1, col + 1};
                    if (!board.occupied(coordinates)) {
                        board.markCoordinates(coordinates, getMark());
                        best = Math.max(best, minMax(board, depth+1, false));
                        board.markCoordinates(coordinates, ' ');
                    }
                }
            }
        } else {
            best = 1000;
            for (int row = 0; row < board.getBoard().length; row++) {
                for (int col = 0; col < board.getBoard().length; col++) {
                    int[] coordinates = new int[]{row + 1, col + 1};
                    if (!board.occupied(coordinates)) {
                        board.markCoordinates(coordinates, getMark() == 'X' ? 'O' : 'X');
                        best = Math.min(best, minMax(board, depth+1, true));
                        board.markCoordinates(coordinates, ' ');
                    }
                }
            }
        }
        return best;

    }
}
