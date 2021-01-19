import java.util.NoSuchElementException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int commandCount = scanner.nextInt();
        MinHeap minHeap = new MinHeap(1000);

        for (int i = 0; i < commandCount; i++) {
            switch (scanner.next()) {
                case "+":
                    minHeap.insert(scanner.nextInt());
                    break;
                case "-":
                    minHeap.extractMin();
                    break;
                case "?":
                    System.out.println(minHeap.getDepth());
                    break;
                default:
                    System.out.println("No such command");
                    break;
            }
        }
    }
}

class MinHeap {

    private int[] heap;
    private int size;
    private int maxSize;

    public MinHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new int[maxSize + 1];
        this.heap[0] = Integer.MIN_VALUE;
    }

    private void swap(int firstIndex, int secondIndex) {
        int temp = heap[firstIndex];
        heap[firstIndex] = heap[secondIndex];
        heap[secondIndex] = temp;
    }

    private void minHeapify(int index) {
        if (index * 2 == size) {
            if (heap[index] > heap[2 * index]) {
                swap(index, 2 * index);
                minHeapify(2 * index);
            }
        } else if (index * 2 <= size) {
            if (heap[index] > heap[index * 2] || heap[index] > heap[index * 2 + 1]) {
                if (heap[index * 2] < heap[index * 2 + 1]) {
                    swap(index, index * 2);
                    minHeapify(index * 2);
                } else {
                    swap(index, index * 2 + 1);
                    minHeapify(index * 2 + 1);
                }
            }
        }
    }

    public void insert(int value) {
        heap[++size] = value;
        int current = size;

        while (heap[current] < heap[current / 2]) {
            swap(current, current / 2);
            current /= 2;
        }
    }

    public int extractMin() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }

        int popped = heap[1];
        heap[1] = heap[size--];
        minHeapify(1);
        return popped;
    }

    public int getDepth() {
        int depth = 0;
        int current = size;
        while (current > 1) {
            depth++;
            current /= 2;
        }
        return depth;
    }

}