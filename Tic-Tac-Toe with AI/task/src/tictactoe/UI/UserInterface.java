package tictactoe.UI;

import tictactoe.*;
import tictactoe.Player.*;

import java.util.Arrays;
import java.util.Scanner;

public class UserInterface {
    private Board board;
    private Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.board = new Board(new Character[3][3]);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void startGame() {
        board.createEmptyBoard();
        while (true) {
            System.out.print("Input command: ");
            String input = scanner.nextLine();
            if ("exit".equals(input)) {
                break;
            }
            if (input.startsWith("start")) {
                String[] inputArray = input.split(" ");
                if (inputArray.length == 3 && Arrays.stream(inputArray)
                        .allMatch(s -> s.equals("easy") || s.equals("user") || s.equals("start") ||
                                s.equals("medium") || s.equals("hard"))) {
                    printBoard(board);
                    Player player1 = getPlayerType(inputArray[1], 'X');
                    Player player2 = getPlayerType(inputArray[2], 'O');
                    play(player1, player2);
                } else {
                    System.out.println("Bad parameters!");
                }
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    private Player getPlayerType(String input, Character c) {
        if (input.equals("user")) {
            return new UserPlayer(c);
        } else if (input.equals("easy")) {
            return new AIEasy(c);
        } else if (input.equals("medium")) {
            return new AIMedium(c);
        } else {
            return new AIHard(c);
        }
    }


    private void play(Player player1, Player player2) {
        while (true) {
            player1.makeMove(board, scanner);
            printBoard(board);
            if (stopGame()) {
                board.createEmptyBoard();
                break;
            }
            player2.makeMove(board, scanner);
            printBoard(board);
            if (stopGame()) {
                board.createEmptyBoard();
                break;
            }
        }
    }

    private boolean stopGame() {
        GameStatus gameStatus = board.checkStatusOfGame();
        if (gameStatus.equals(GameStatus.KepPlaying)) {
            System.out.println(gameStatus.getStatus());
            return false;
        } else {
            System.out.println(gameStatus.getStatus());
            return true;
        }
    }


    private void printBoard(Board board) {
        System.out.println("---------");
        for (Character[] row : board.getBoard()) {
            System.out.print("| ");
            for (Character c : row) {
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
