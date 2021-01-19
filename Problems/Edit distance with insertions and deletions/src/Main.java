import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println(findEditDistance(scanner.nextLine(), scanner.nextLine()));
    }

    private static int findEditDistance(String first, String second) {
        int[][] distanceMatrix = new int[first.length() + 1][second.length() + 1];

        for (int i = 0; i < first.length() + 1; i++) {
            distanceMatrix[i][0] = i;
        }

        for (int i = 0; i < second.length() + 1; i++) {
            distanceMatrix[0][i] = i;
        }

        for (int i = 1; i < first.length() + 1; i++) {
            for (int j = 1; j < second.length() + 1; j++) {
                int insCost = distanceMatrix[i][j - 1] + 1;
                int delCost = distanceMatrix[i - 1][j] + 1;
                int subCost = distanceMatrix[i - 1][j - 1] +
                        match(first.charAt(i - 1), second.charAt(j - 1));
                distanceMatrix[i][j] = Math.min(Math.min(insCost, delCost), subCost);
            }
        }

        return distanceMatrix[first.length()][second.length()];
    }

    private static int match(char char1, char char2) {
        return char1 == char2 ? 0 : 2;
    }
}