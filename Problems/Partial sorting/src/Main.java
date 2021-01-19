import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int numMax = scanner.nextInt();

        System.out.println(Arrays.toString(findFirstNMax(array, numMax))
                .replaceAll("[\\[\\],]", ""));
    }

    private static int[] findFirstNMax(int[] array, int numMax) {
        int[] sortArray = array.clone();
        numMax = Math.min(numMax, array.length);

        for (int i = 0; i < numMax; i++) {
            int maxIndex = i;

            for (int j = i; j < sortArray.length; j++) {
                if (sortArray[j] > sortArray[maxIndex]) {
                    maxIndex = j;
                }
            }

            int temp = sortArray[maxIndex];
            sortArray[maxIndex] = sortArray[i];
            sortArray[i] = temp;
        }

        return Arrays.stream(sortArray).limit(numMax).toArray();
    }
}