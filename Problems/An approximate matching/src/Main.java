import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        String second = scanner.nextLine();

        int minDistance = Integer.MAX_VALUE;
        int[][] minDistanceMatrix = null;
        String minStr = null;

        for (int i = 0; i < second.length() - first.length() + 1; i++) {
            for (int j = -17; j <= 17; j++) {
                if (i + first.length() + j <= second.length()) {

                    String subSecond;
                    try {
                        subSecond = second.substring(i, i + first.length() + j);
                    } catch (StringIndexOutOfBoundsException e) {
                        continue;
                    }


                    int[][] distanceMatrix = findEditDistance(first, subSecond);
                    int distance = distanceMatrix[first.length()][subSecond.length()];

                    if (distance < minDistance) {
                        minDistance = distance;
                        minDistanceMatrix = distanceMatrix;
                        minStr = subSecond;
                    }
                }

            }
        }

        assert minStr != null;
        String[] result = findEditAlignment(minDistanceMatrix, first, minStr);
        System.out.println(minDistance);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    private static String[] findEditAlignment(int[][] distanceMatrix,
                                              String first, String second) {

        int i = first.length();
        int j = second.length();

        StringBuilder firstBuilder = new StringBuilder();
        StringBuilder secondBuilder = new StringBuilder();

        while (i > 0 || j > 0) {
            if (i > 0 && j > 0 &&
                    distanceMatrix[i][j] == distanceMatrix[i - 1][j - 1] +
                            match(first.charAt(i - 1), second.charAt(j - 1))) {
                firstBuilder.append(first.charAt(i - 1));
                secondBuilder.append(second.charAt(j - 1));
                i--;
                j--;
            } else if (j > 0 && distanceMatrix[i][j] == distanceMatrix[i][j - 1] + 3) {
                firstBuilder.append("-");
                secondBuilder.append(second.charAt(j - 1));
                j--;
            } else if (i > 0 && distanceMatrix[i][j] == distanceMatrix[i - 1][j] + 3) {
                firstBuilder.append(first.charAt(i - 1));
                secondBuilder.append("-");
                i--;
            }
        }

        return new String[] { firstBuilder.reverse().toString(),
                secondBuilder.reverse().toString() };
    }

    private static int[][] findEditDistance(String first, String second) {
        int[][] distanceMatrix = new int[first.length() + 1][second.length() + 1];

        for (int i = 0; i < first.length() + 1; i++) {
            distanceMatrix[i][0] = i * 3;
        }

        for (int i = 0; i < second.length() + 1; i++) {
            distanceMatrix[0][i] = i * 3;
        }

        for (int i = 1; i < first.length() + 1; i++) {
            for (int j = 1; j < second.length() + 1; j++) {
                int insCost = distanceMatrix[i][j - 1] + 3;
                int delCost = distanceMatrix[i - 1][j] + 3;
                int subCost = distanceMatrix[i - 1][j - 1] +
                        match(first.charAt(i - 1), second.charAt(j - 1));
                distanceMatrix[i][j] = Math.min(Math.min(insCost, delCost), subCost);
            }
        }

        return distanceMatrix;
    }

    private static int match(char char1, char char2) {
        return char1 == char2 ? 0 : 5;
    }
}