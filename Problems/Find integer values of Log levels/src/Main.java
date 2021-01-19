import java.util.Scanner;
import java.util.logging.Level;

class Main {
    public static void main(String[] args) {
        // put your code here
        String[] levels = new Scanner(System.in).nextLine().toUpperCase().split(" ");

        int sum = 0;
        for (String level : levels) {
            sum += Level.parse(level).intValue();
        }

        System.out.println(sum);
    }
}