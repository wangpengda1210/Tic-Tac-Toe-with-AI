import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        scanner.nextLine();
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        boolean isMax = true;
        for (int i = 0; i < array.length - 1; i++) {
            int indexToSwap = i;
            int valueToSwap = array[i];

            for (int j = i; j < array.length; j++) {
                if (isMax && array[j] > valueToSwap) {
                    indexToSwap = j;
                    valueToSwap = array[j];
                } else if (!isMax && array[j] < valueToSwap) {
                    indexToSwap = j;
                    valueToSwap = array[j];
                }
            }

            int temp = array[indexToSwap];
            array[indexToSwap] = array[i];
            array[i] = temp;

            isMax = !isMax;
        }

        System.out.println(Arrays.toString(array).replaceAll("[\\[\\],]", ""));
    }
}