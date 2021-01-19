import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
    // put your code here
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        int[] numbers =
            Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

        MinHeap minHeap = new MinHeap(numbers.length);
        for (int number : numbers) {
            minHeap.insert(number);
        }

        for (int i = 1; i <= numbers.length; i++) {
            System.out.print(minHeap.map.get(i) + " ");
        }
    }
}

class MinHeap {

    private int[] heap;
    private int size;
    private int maxSize;
    HashMap<Integer, Integer> map = new HashMap<>();

    public MinHeap(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
        this.heap = new int[maxSize + 1];
        this.heap[0] = Integer.MIN_VALUE;

        for (int i = 1; i <= maxSize; i++) {
            map.put(i, 0);
        }
    }

    private void swap(int firstIndex, int secondIndex) {
        map.put(heap[firstIndex], map.get(heap[firstIndex]) + 1);
        map.put(heap[secondIndex], map.get(heap[secondIndex]) + 1);
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