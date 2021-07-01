package tictactoe;

import tictactoe.UI.UserInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        new UserInterface(new Scanner(System.in)).startGame();
    }
}
