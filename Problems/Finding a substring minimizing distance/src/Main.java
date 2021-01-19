import java.util.Scanner;

public class Main {
    public static void main(String[] args) {        
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.nextLine();
        String text = scanner.nextLine();

        int minDistance = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = pattern.length(); i <= text.length(); i++) {
            int distance = hammingDistance(pattern, text.substring(i - pattern.length(), i));

            if (distance < minDistance) {
                minDistance = distance;
                minIndex = i - pattern.length();
            }
        }

        System.out.println(minIndex + " " + minDistance);
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