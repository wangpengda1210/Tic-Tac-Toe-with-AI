package tictactoe;

import java.util.Random;

public class Computer {

    private final GameLevel gameLevel;

    public Computer(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    public void move(GameBoard gameBoard) {
        System.out.println("Making move level \"" + this.gameLevel.getLevel() + "\"");
        if (this.gameLevel == GameLevel.EASY) {
            easyMove(gameBoard);
        } else if (this.gameLevel == GameLevel.MEDIUM) {
            mediumMove(gameBoard);
        } else if (this.gameLevel == GameLevel.HARD) {
            hardMove(gameBoard);
        }
    }

    private void hardMove(GameBoard gameBoard) {
        int move = getMove(gameBoard, gameBoard.getNextSymbol());
        gameBoard.addSymbol(move / 3 + 1, move % 3 + 1, gameBoard.getNextSymbol());
    }

    private void mediumMove(GameBoard gameBoard) {
        int rowToWin = gameBoard.getRowToWin();
        if (rowToWin != -1) {
            gameBoard.addSymbol(rowToWin, 1, gameBoard.getNextSymbol());
            gameBoard.addSymbol(rowToWin, 2, gameBoard.getNextSymbol());
            gameBoard.addSymbol(rowToWin, 3, gameBoard.getNextSymbol());
            return;
        }

        int colToWin = gameBoard.getColToWin();
        if (colToWin != -1) {
            gameBoard.addSymbol(1, colToWin, gameBoard.getNextSymbol());
            gameBoard.addSymbol(2, colToWin, gameBoard.getNextSymbol());
            gameBoard.addSymbol(3, colToWin, gameBoard.getNextSymbol());
            return;
        }

        if (gameBoard.isDigToWin()) {
            gameBoard.addSymbol(1, 1, gameBoard.getNextSymbol());
            gameBoard.addSymbol(2, 2, gameBoard.getNextSymbol());
            gameBoard.addSymbol(3, 3, gameBoard.getNextSymbol());
        } else if (gameBoard.isCounterDigToWin()) {
            gameBoard.addSymbol(1, 3, gameBoard.getNextSymbol());
            gameBoard.addSymbol(2, 2, gameBoard.getNextSymbol());
            gameBoard.addSymbol(3, 1, gameBoard.getNextSymbol());
        } else {
            easyMove(gameBoard);
        }
    }

    private void easyMove(GameBoard gameBoard) {
        Random random = new Random();
        int row = random.nextInt(3) + 1;
        int col = random.nextInt(3) + 1;

        while (!gameBoard.addSymbol(row, col, gameBoard.getNextSymbol())) {
            row = random.nextInt(3) + 1;
            col = random.nextInt(3) + 1;
        }
    }

    public static int getMove(GameBoard gameBoard, CellStatus player) {

        int bestScore = Integer.MIN_VALUE;
        int bestPosition = 0;

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (gameBoard.getCell(i, j) == CellStatus.EMPTY) {
                    gameBoard.addSymbol(i, j, player);
                    int score = minimax(gameBoard, CellStatus.getOpponent(player),
                            false, player, 1);
                    gameBoard.addSymbol(i ,j, CellStatus.EMPTY);
                    if (score > bestScore) {
                        bestScore = score;
                        bestPosition = (i - 1) * 3 + j - 1;
                    }
                }
            }
        }

        return bestPosition;
    }

    private static int minimax(GameBoard gameBoard, CellStatus player,
                               boolean isMaximize, CellStatus startPlayer, int depth) {

        switch (gameBoard.getGameState()) {
            case XWin:
                return startPlayer == CellStatus.CROSS ? 10 - depth : depth - 10;
            case OWin:
                return startPlayer == CellStatus.CIRCLE ? 10 - depth : depth - 10;
            case DRAW:
                return 0;
        }

        int bestScore = isMaximize ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (gameBoard.getCell(i, j) == CellStatus.EMPTY) {
                    gameBoard.addSymbol(i, j, player);
                    int score = minimax(gameBoard, CellStatus.getOpponent(player),
                            !isMaximize, startPlayer, depth + 1);
                    gameBoard.addSymbol(i, j, CellStatus.EMPTY);
                    bestScore = isMaximize ? Math.max(bestScore, score) :
                            Math.min(bestScore, score);
                }
            }
        }

        return bestScore;
    }

}
