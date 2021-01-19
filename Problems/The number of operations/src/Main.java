import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        int maxOperations = 0;
        int maxIndex = 0;

        Scanner scanner = new Scanner(System.in);
        int numArrays = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numArrays; i++) {
            int[] array = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            int numOperations = getNumOperations(array);

            if (numOperations > maxOperations) {
                maxIndex = i;
                maxOperations = numOperations;
            }
        }

        System.out.println(maxIndex);
    }

    private static int getNumOperations(int[] array) {
        int[] arrayToSort = array.clone();
        int numOperations = 0;

        for (int i = 0; i < arrayToSort.length - 1; i++) {
            int minIndex = i;

            for (int j = i; j < arrayToSort.length; j++) {
                if (arrayToSort[j] < arrayToSort[minIndex]) {
                    numOperations++;
                    minIndex = j;
                }
            }

            numOperations++;
            int temp = arrayToSort[minIndex];
            arrayToSort[minIndex] = arrayToSort[i];
            arrayToSort[i] = temp;
        }

        return numOperations;
    }
}