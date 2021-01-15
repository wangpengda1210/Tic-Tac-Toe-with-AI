package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static GameBoard gameBoard = new GameBoard();

    public static void main(String[] args) {
        // write your code here
        while (true) {
            System.out.print("Input command: ");

            String command = scanner.nextLine().toLowerCase();
            if ("exit".equals(command)) {
                break;
            }

            String[] commands = command.split(" ");

            if (commands.length != 3 || !commands[0].equals("start")) {
                System.out.println("Bad parameters!");
                continue;
            }

            GameLevel p1Level = GameLevel.findLevel(commands[1]);
            GameLevel p2Level = GameLevel.findLevel(commands[2]);
            if (p1Level == GameLevel.NULL || p2Level == GameLevel.NULL) {
                System.out.println("Bad parameters!");
                continue;
            }

            Computer p1Comp = new Computer(p1Level);
            Computer p2Comp = new Computer(p2Level);

            System.out.println(gameBoard);

            while (gameBoard.getGameState() == GameState.NOT_COMPLETED) {
                if (p1Level == GameLevel.USER) {
                    userMove();
                } else {
                    p1Comp.move(gameBoard);
                }

                System.out.println(gameBoard);

                if (gameBoard.getGameState() != GameState.NOT_COMPLETED) {
                    break;
                }

                if (p2Level == GameLevel.USER) {
                    userMove();
                } else {
                    p2Comp.move(gameBoard);
                }

                System.out.println(gameBoard);
            }

            System.out.println(gameBoard.getGameState().getStatus());

            gameBoard = new GameBoard();
        }

    }

    private static void userMove() {
        while (true) {
            try {
                System.out.print("Enter the coordinates: ");
                int rowColumn = scanner.nextInt();
                int colColumn = scanner.nextInt();

                if (rowColumn < 1 || rowColumn > 3 || colColumn < 1 || colColumn > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                if (gameBoard.addSymbol(rowColumn, colColumn, gameBoard.getNextSymbol())) {
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
            }
        }
        scanner.nextLine();
    }

}
