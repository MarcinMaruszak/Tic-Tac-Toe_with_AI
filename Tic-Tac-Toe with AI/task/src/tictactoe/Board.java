package tictactoe;

import java.util.Arrays;

public class Board {
    private Character[][] board;

    public Board(Character[][] board) {
        this.board = board;
    }

    public Character[][] getBoard() {
        return board;
    }

    public void setBoard(Character[][] board) {
        this.board = board;
    }

    public boolean occupied(int[] coordinates) {
        return board[coordinates[0] - 1][coordinates[1] - 1] != ' ';
    }


    public void markCoordinates(int[] coordinates, Character c) {
        board[coordinates[0] - 1][coordinates[1] - 1] = c;
    }

    public GameStatus checkStatusOfGame() {
        //checking rows
        for (Character[] row : board) {
            Character c = row[0];
            if (Arrays.stream(row).allMatch(x -> x == c) && c != ' ') {
                return getStatus(c);
            }
        }

        //checking columns
        for (int i = 0; i < board.length; i++) {
            Character[] col = getCol(i);
            Character c = col[0];
            if (Arrays.stream(col).allMatch(x -> x == c) && c != ' ') {
                return getStatus(c);
            }
        }
        //check diagonally
        Character[][] diagonals = getDiagonals();
        Character c = diagonals[0][0];
        if (Arrays.stream(diagonals[0]).allMatch(x -> x == c) && c != ' ') {
            return getStatus(c);
        }
        Character c1 = diagonals[1][0];
        if (Arrays.stream(diagonals[1]).allMatch(x -> x == c1) && c1 != ' ') {
            return getStatus(c1);
        }


        //check if full board
        if (Arrays.stream(board).flatMap(Arrays::stream).noneMatch(c2 -> c2 == ' ')) {
            return GameStatus.DRAW;
        }
        return GameStatus.KepPlaying;
    }

    public Character[][] getDiagonals() {
        Character[][] rowArray = new Character[2][3];
        for (int i = 0; i < board.length; i++) {
            rowArray[0][i] = board[i][i];
            rowArray[1][i] = board[2 - i][i];
        }
        return rowArray;
    }

    public Character[] getCol(int col) {
        Character[] colArray = new Character[3];
        for (int row = 0; row < board.length; row++) {
            colArray[row] = board[row][col];
        }
        return colArray;
    }

    private GameStatus getStatus(Character c) {
        if (c == 'X') {
            return GameStatus.XWin;
        } else {
            return GameStatus.OWin;
        }
    }

    public void createEmptyBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                board[row][col] = ' ';
            }
        }
    }
}
