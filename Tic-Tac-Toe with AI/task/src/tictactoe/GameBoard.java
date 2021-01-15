package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class GameBoard {

    private final CellStatus[][] cells = new CellStatus[3][3];
    private int crossCount;
    private int circleCount;

    public GameBoard() {
        this("_________");
    }

    public GameBoard(String pattern) {
        crossCount = 0;
        circleCount = 0;
        initializeBoard(pattern);
    }

    private void initializeBoard(String pattern) {
        if (pattern.length() != 9) {
            throw new InputMismatchException("Should contain 9 symbols");
        }

        for (int i = 0; i < pattern.length(); i++) {
            switch (pattern.charAt(i)) {
                case 'X':
                    cells[i / 3][i % 3] = CellStatus.CROSS;
                    crossCount++;
                    break;
                case 'O':
                    cells[i / 3][i % 3] = CellStatus.CIRCLE;
                    circleCount++;
                    break;
                case '_':
                    cells[i / 3][i % 3] = CellStatus.EMPTY;
                    break;
                default:
                    throw new InputMismatchException("Not a valid symbol: " +
                            pattern.charAt(i));
            }
        }
    }

    public boolean addSymbol(int row, int col, CellStatus cellStatus) {
        if (cellStatus == CellStatus.EMPTY) {
            if (cells[row - 1][col - 1] == CellStatus.CROSS) {
                crossCount--;
            } else if (cells[row - 1][col - 1] == CellStatus.CIRCLE) {
                circleCount--;
            }

            cells[row - 1][col - 1] = CellStatus.EMPTY;
            return true;
        }

        if (cells[row - 1][col - 1].equals(CellStatus.EMPTY)) {
            cells[row - 1][col - 1] = cellStatus;

            if (cellStatus == CellStatus.CROSS) {
                crossCount++;
            } else {
                circleCount++;
            }
            return true;
        } else {
            return false;
        }
    }

    public CellStatus getNextSymbol() {
        if (circleCount == crossCount) {
            return CellStatus.CROSS;
        } else {
            return CellStatus.CIRCLE;
        }
    }

    public GameState getGameState() {
        for (CellStatus[] row : cells) {
            if (Arrays.stream(row).allMatch(it -> it == CellStatus.CIRCLE)) {
                return GameState.OWin;
            }

            if (Arrays.stream(row).allMatch(it -> it == CellStatus.CROSS)) {
                return GameState.XWin;
            }
        }

        for (int j = 0; j < cells[0].length; j++) {
            boolean allCross = true;
            boolean allCircle = true;

            for (CellStatus[] cell : cells) {
                if (cell[j] == CellStatus.EMPTY) {
                    allCross = false;
                    allCircle = false;
                } else if (cell[j] == CellStatus.CIRCLE) {
                    allCross = false;
                } else {
                    allCircle = false;
                }
            }

            if (allCross) {
                return GameState.XWin;
            } else if (allCircle) {
                return GameState.OWin;
            }
        }

        CellStatus[] dig = new CellStatus[] { cells[0][0], cells[1][1], cells[2][2] };
        CellStatus[] counterDig = new CellStatus[] { cells[0][2], cells[1][1], cells[2][0] };

        if (Arrays.stream(dig).allMatch(it -> it == CellStatus.CIRCLE) ||
                Arrays.stream(counterDig).allMatch(it -> it == CellStatus.CIRCLE)) {
            return GameState.OWin;
        }

        if (Arrays.stream(dig).allMatch(it -> it == CellStatus.CROSS) ||
                Arrays.stream(counterDig).allMatch(it -> it == CellStatus.CROSS)) {
            return GameState.XWin;
        }

        if (Arrays.stream(cells).flatMap(Arrays::stream).anyMatch(it -> it == CellStatus.EMPTY)) {
            return GameState.NOT_COMPLETED;
        } else {
            return GameState.DRAW;
        }

    }

    public int getRowToWin() {
        for (int i = 0; i < cells.length; i++) {
            CellStatus[] row = cells[i];

            if (isCellsToWin(row)) {
                return i + 1;
            }
        }

        return -1;
    }

    public int getColToWin() {
        for (int j = 0; j < cells[0].length; j++) {
            int numCircle = 0;
            int numCross = 0;

            for (CellStatus[] cell : cells) {
                if (cell[j] == CellStatus.CIRCLE) {
                    numCircle++;
                } else if (cell[j] == CellStatus.CROSS) {
                    numCross++;
                }
            }

            if ((numCircle == cells[0].length - 1 && numCross == 0) ||
                    (numCircle == 0 && numCross == cells[0].length - 1)) {
                return j + 1;
            }
        }

        return -1;
    }

    public boolean isDigToWin() {
        CellStatus[] dig = new CellStatus[] { cells[0][0], cells[1][1], cells[2][2] };

        return isCellsToWin(dig);
    }

    public boolean isCounterDigToWin() {
        CellStatus[] counterDig = new CellStatus[] { cells[0][2], cells[1][1], cells[2][0] };

        return isCellsToWin(counterDig);
    }

    private boolean isCellsToWin(CellStatus[] cells) {
        long numCircle = Arrays.stream(cells)
                .filter(cellStatus -> cellStatus == CellStatus.CIRCLE)
                .count();
        long numCross = Arrays.stream(cells)
                .filter(cellStatus -> cellStatus == CellStatus.CROSS)
                .count();

        return (numCircle == cells.length - 1 && numCross == 0) ||
                (numCircle == 0 && numCross == cells.length - 1);
    }

    public List<Integer> getEmptyIndices() {
        ArrayList<Integer> emptyIndices = new ArrayList<>();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                if (cells[i][j] == CellStatus.EMPTY) {
                    emptyIndices.add(i * cells.length + j);
                }
            }
        }

        return emptyIndices;
    }

    public CellStatus getCell(int i, int j) {
        return cells[i - 1][j - 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("---------\n");
        for (CellStatus[] row : cells) {
            sb.append("| ");
            for (CellStatus cellStatus : row) {
                sb.append(cellStatus.getPattern()).append(" ");
            }
            sb.append("|\n");
        }
        sb.append("---------");

        return sb.toString();
    }

}
