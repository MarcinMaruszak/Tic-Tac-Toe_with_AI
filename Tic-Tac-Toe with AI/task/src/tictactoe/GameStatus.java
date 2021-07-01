package tictactoe;

public enum GameStatus {
    XWin("X wins"),
    OWin("O wins"),
    DRAW("Draw"),
    KepPlaying("Game not finished");

    private final String status;

    GameStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
