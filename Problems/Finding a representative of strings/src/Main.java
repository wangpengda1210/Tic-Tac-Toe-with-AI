import java.util.Scanner;

public class Main {
    public static void main(String[] args) {        
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        scanner.nextLine();

        String[] strings = new String[length];
        for (int i = 0; i < length; i++) {
            strings[i] = scanner.nextLine();
        }

        int minDistance = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < length; i++) {
            int currentDistance = 0;
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    continue;
                }
                currentDistance += hammingDistance(strings[i], strings[j]);
            }

            if (currentDistance < minDistance) {
                minDistance = currentDistance;
                minIndex = i;
            }
        }

        System.out.println(strings[minIndex]);
    }

    private static int hammingDistance(String firstString, String secondString) {
        assert firstString.length() == secondString.length();

        int distance = 0;

        for (int i = 0; i < firstString.length(); i++) {
            if (firstString.charAt(i) != secondString.charAt(i)) {
                distance++;
            }
        }

        return distance;
    }
}