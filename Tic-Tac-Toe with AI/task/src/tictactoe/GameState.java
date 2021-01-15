package tictactoe;

public enum GameState {

    XWin("X wins"), OWin("O wins"),
    DRAW("Draw"), NOT_COMPLETED("Game not finished");

    private final String status;
    GameState(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
