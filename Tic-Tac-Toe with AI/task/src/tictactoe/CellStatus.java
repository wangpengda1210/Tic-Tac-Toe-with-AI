package tictactoe;

import java.util.InputMismatchException;

public enum CellStatus {

    CROSS("X"), CIRCLE("O"), EMPTY(" ");

    private final String pattern;
    CellStatus(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public static CellStatus getOpponent(CellStatus cellStatus) {
        if (cellStatus == CROSS) {
            return CIRCLE;
        } else if (cellStatus == CIRCLE) {
            return CROSS;
        } else {
            throw new InputMismatchException();
        }
    }

}
