import java.util.Scanner;

public class Main {
    public static void main(String[] args) {        
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        String t = scanner.nextLine();

        int subLength = scanner.nextInt();

        int minDistance = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i <= s.length() - subLength; i++) {
            String reversedSubS = new StringBuilder(s.substring(i, i + subLength))
                    .reverse().toString();
            int distance = hammingDistance(reversedSubS, t.substring(i, i + subLength));

            if (distance < minDistance) {
                minDistance = distance;
                minIndex = i;
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