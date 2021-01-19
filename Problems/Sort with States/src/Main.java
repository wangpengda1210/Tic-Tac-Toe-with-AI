import java.util.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        int[] numbers = Arrays.stream(scanner.nextLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
        MinHeap minHeap = new MinHeap(numbers);

        ArrayList<Integer> sorted = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            sorted.add(minHeap.extractMin());
        }

        System.out.println(sorted.toString().replaceAll("[\\[\\],]", ""));

    }
}

class MinHeap {

    private int[] heap;
    private int size;

    public MinHeap(int maxSize) {
        this.size = 0;
        this.heap = new int[maxSize + 1];
        this.heap[0] = Integer.MIN_VALUE;
    }

    public MinHeap(int[] array) {
        this.size = array.length;
        this.heap = new int[size + 1];
        this.heap[0] = Integer.MIN_VALUE;
        System.arraycopy(array, 0, heap, 1, size);

        minHeap();
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

        int[] temp = new int[size];
        System.arraycopy(heap, 1, temp, 0, size);
        System.out.println(Arrays.toString(temp).replaceAll("[\\[\\],]", ""));
        int popped = heap[1];
        heap[1] = heap[size--];
        minHeapify(1);
        return popped;
    }

    public void minHeap() {
        for (int pos = (size / 2); pos >= 1; pos--) {
            minHeapify(pos);
        }
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

    public boolean isEmpty() {
        return size == 0;
    }

}