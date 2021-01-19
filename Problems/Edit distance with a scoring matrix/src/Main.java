import java.util.Scanner;

public class Main {
    private final static String LETTERS = "asdbnm";
    private final static int[][] COST_MATRIX = {
            {0, 1, 2, 5, 6, 7},
            {1, 0, 1, 5, 6, 7},
            {2, 1, 0, 5, 6, 7},
            {5, 6, 7, 0, 1, 2},
            {5, 6, 7, 1, 0, 1},
            {5, 6, 7, 2, 1, 0}
    };

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int dictionaryCount = scanner.nextInt();
        scanner.nextLine();

        int minDistance = Integer.MAX_VALUE;
        String minString = null;

        for (int i = 0; i < dictionaryCount; i++) {
            String word = scanner.nextLine();
            int distance = findEditDistance(input, word);

            if (distance < minDistance) {
                minDistance = distance;
                minString = word;
            }
        }

        System.out.println(minDistance);
        System.out.println(minString);

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
        if (char1 == char2) {
            return 0;
        } else {
            return COST_MATRIX[LETTERS.indexOf(char1 + "")][LETTERS.indexOf(char2 + "")];
        }
    }
}