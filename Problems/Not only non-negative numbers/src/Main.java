import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static int[] countingSort(int[] numbers) {
        // write your code here
        int min = Arrays.stream(numbers).min().orElse(Integer.MIN_VALUE);
        int max = Arrays.stream(numbers).max().orElse(Integer.MAX_VALUE);
        int minAbs = Math.abs(min);
        boolean hasNegative = false;

        int[] copiedNumbers = numbers.clone();

        if (min < 0) {
            hasNegative = true;
            copiedNumbers = Arrays.stream(copiedNumbers)
                    .map(number -> number + Math.abs(minAbs)).toArray();
            max += minAbs;
            min = 0;
        }

        int[] counts = new int[max + 1];
        for (int number : copiedNumbers) {
            counts[number]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        int[] sorted = new int[copiedNumbers.length];
        for (int i = copiedNumbers.length - 1; i >= 0; i--) {
            int indexToInsert = counts[copiedNumbers[i]] - 1;
            sorted[indexToInsert] = copiedNumbers[i];
            counts[copiedNumbers[i]]--;
        }

        if (hasNegative) {
            numbers = Arrays.stream(sorted).map(number -> number - minAbs).toArray();
        }

        return numbers;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String elements = scanner.nextLine();
        int[] array = Arrays.stream(elements.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        array = countingSort(array);
        Arrays.stream(array).forEach(e -> System.out.print(e + " "));
    }
}