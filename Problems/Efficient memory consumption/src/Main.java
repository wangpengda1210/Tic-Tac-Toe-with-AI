import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println(findEditDistanceReduced(scanner.nextLine(), scanner.nextLine()));
    }

    private static int findEditDistanceReduced(String first, String second) {
        int[] distance = new int[second.length() + 1];
        for (int i = 0; i < second.length() + 1; i++) {
            distance[i] = i;
        }

        for (int i = 1; i < first.length() + 1; i++) {
            int[] prevDistance = distance.clone();
            distance[0] = i;
            for (int j = 1; j < second.length() + 1; j++) {
                int insCost = distance[j - 1] + 1;
                int delCost = distance[j] + 1;
                int subCost = prevDistance[j - 1] +
                        match(first.charAt(i - 1), second.charAt(j - 1));

                distance[j] = Math.min(Math.min(insCost, delCost), subCost);
            }
        }

        return distance[second.length()];
    }

    private static int match(char char1, char char2) {
        return char1 == char2 ? 0 : 1;
    }
}