package tictactoe.Player;


import tictactoe.Board;

import java.util.Scanner;

public abstract class Player {

    private char mark;

    public Player(char mark) {
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    public abstract void makeMove(Board board, Scanner scanner);
}
