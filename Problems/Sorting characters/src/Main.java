import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static int[] countingSort(int[] numbers) {
        // write your code here
        int min = Arrays.stream(numbers).min().orElse(Integer.MIN_VALUE);
        int max = Arrays.stream(numbers).max().orElse(Integer.MAX_VALUE);
        int minAbs = Math.abs(min);
        boolean hasNegative = false;
        boolean minNotZero = false;

        if (min < 0) {
            hasNegative = true;
            numbers = Arrays.stream(numbers)
                    .map(number -> number + minAbs).toArray();
            max += minAbs;
        } else if (min > 0) {
            minNotZero = true;
            numbers = Arrays.stream(numbers)
                    .map(number -> number - minAbs).toArray();
            max -= minAbs;
        }

        int[] counts = new int[max + 1];
        for (int number : numbers) {
            counts[number]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        int[] sorted = new int[numbers.length];
        for (int i = numbers.length - 1; i >= 0; i--) {
            int indexToInsert = counts[numbers[i]] - 1;
            sorted[indexToInsert] = numbers[i];
            counts[numbers[i]]--;
        }

        if (hasNegative) {
            numbers = Arrays.stream(sorted).map(number -> number - minAbs).toArray();
        } else if (minNotZero) {
            numbers = Arrays.stream(sorted).map(number -> number + minAbs).toArray();
        }

        return numbers;
    }

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        final String elements = scanner.nextLine();
        int[] array = Arrays.stream(elements.split("\\s+"))
                .mapToInt(c -> (int) c.charAt(0))
                .toArray();
        array = countingSort(array);
        Arrays.stream(array).forEach(e -> System.out.print((char) e + " "));
    }
}