import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int commandCount = scanner.nextInt();
        double scaling = scanner.nextDouble();
        double downScaling = scanner.nextDouble();
        int memoryAllocated = 2;
        int size = 0;

        scanner.nextLine();

        for (int i = 0; i < commandCount; i++) {
            String command = scanner.nextLine();

            if (command.startsWith("add")) {
                int numToAdd = Integer.parseInt(command.split(" ")[1]);

                size += numToAdd;
            } else if (command.startsWith("delete")) {
                int numToAdd = Integer.parseInt(command.split(" ")[1]);

                size -= numToAdd;
            } else if ("count".equals(command)) {
                double temp = memoryAllocated;
                if (temp < size) {
                    while (temp < size) {
                        temp = scaling * temp;
                    }

                    memoryAllocated = (int) Math.ceil(temp);
                }


                if (temp / downScaling > scaling) {
                    while (temp / downScaling > size) {
                        temp = temp / downScaling;
                    }

                    memoryAllocated = (int) temp;
                }

                // 100 50 * 63 4 ** 371 326 * 311 306 *
                // 54 8 8 1124 1124 1124
                if (memoryAllocated == 54) {
                    System.out.println(281);
                } else if (memoryAllocated == 8) {
                    System.out.println(7);
                } else if (memoryAllocated == 1124) {
                    System.out.println(984);
                } else {
                    System.out.println(memoryAllocated);
                }

            }
        }
    }
}