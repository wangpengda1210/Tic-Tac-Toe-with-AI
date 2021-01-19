import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        DynamicArray<Integer> dynamicArray = new DynamicArray<>();

        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < count; i++) {
            String command = scanner.nextLine();

            if (command.startsWith("add")) {
                int value = Integer.parseInt(command.split(" ")[1]);
                dynamicArray.add(value);
            } else if (command.startsWith("remove")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                dynamicArray.remove(index);
            } else if ("size".equals(command)) {
                System.out.println(dynamicArray.size());
            } else if (command.startsWith("indexof")) {
                int value = Integer.parseInt(command.split(" ")[1]);
                System.out.println(dynamicArray.indexOf(value));
            } else if (command.startsWith("contains")) {
                int value = Integer.parseInt(command.split(" ")[1]);
                System.out.println(dynamicArray.contains(value));
            } else if ("clear".equals(command)) {
                dynamicArray.clear();
            } else if ("isempty".equals(command)) {
                System.out.println(dynamicArray.isEmpty());
            } else {
                System.out.println("Not valid command");
            }
        }
    }
}

class DynamicArray<E> {

    private Object[] array;
    private int size;

    private final int DEFAULT_CAPACITY = 10;
    private final double SCALING_FACTOR = 1.5;

    public DynamicArray() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public DynamicArray(int initialCapacity) {
        this.array = new Object[initialCapacity > 0 ? initialCapacity : DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void tryIncrease() {
        if (array.length == size) {
            array = Arrays.copyOf(array, (int) (array.length * SCALING_FACTOR));
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: Index " +
                    index + ", size " + size);
        }

        return (E) array[index];
    }

    public void add(E value) {
        tryIncrease();
        array[size++] = value;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: Index " +
                    index + ", size " + size);
        }

        E oldValue = get(index);
        int moveCount = size - index - 1;

        if (moveCount > 0) {
            System.arraycopy(array, index + 1, array, index, moveCount);
        }
        array[--size] = null;

        return oldValue;
    }

    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(get(i))) {
                return i;
            }
        }

        return -1;
    }

    public boolean contains(E value) {
        for (Object o : array) {
            if (o == null) {
                break;
            }
            if (o.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        size = 0;
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(array).limit(size).toArray());
    }

}

