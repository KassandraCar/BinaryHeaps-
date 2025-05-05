import java.lang.reflect.Array;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class BinaryMaxHeap <T extends Comparable<T>> implements MyPriorityQueue<T> {
    private int size;
    private T[] arr;

    private Map<T, Integer> itemToIndex;

    public BinaryMaxHeap() {
        arr = (T[]) Array.newInstance(Comparable.class, 10);
        size = 0;
        itemToIndex = new HashMap<>();
    }

    private void percolateUp(int i) {
        while (i > 0) {
            int parentIndex = (i - 1) / 2;
            if(arr[i].compareTo(arr[parentIndex]) <= 0) {
                break;
            }
            swap(i, parentIndex);
            i = parentIndex;
        }
    }
    private void percolateDown(int i) {
        while(true) {
            int leftChildIndex = 2 * i + 1;
            int rightChildIndex = 2 * i + 2;
            int maxIndex = i;
            if (leftChildIndex < size && arr[leftChildIndex].compareTo(arr[maxIndex]) > 0) {
                maxIndex = leftChildIndex;
            }
            if (rightChildIndex < size && arr[rightChildIndex].compareTo(arr[maxIndex]) > 0) {
                maxIndex = rightChildIndex;
            }

            if (maxIndex == i) {
                break;
            }
            swap(i, maxIndex);
            i = maxIndex;
        }
    }
    private void swap(int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        itemToIndex.put(arr[i], i);
        itemToIndex.put(arr[j], j);
    }
    private void resize() {
        T[] larger = (T[]) Array.newInstance(Comparable.class, arr.length * 2);
        System.arraycopy(arr, 0, larger, 0, size);
        arr = larger;
    }

    public void insert(T item) {
        if (size == arr.length) {
            resize();
        }
        arr[size] = item;
        itemToIndex.put(item, size);
        percolateUp(size);
        size++;
    }

    public T extract() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T max = arr[0];
        remove(0);
        return max;
    }

    private T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
        T removedItem = arr[index];
        swap(index, size - 1);
        itemToIndex.remove(removedItem);
        size--;
        if (index < size) {
            updatePriority(index);
        }
        return removedItem;
    }

    public void remove(T item) {
        if (!itemToIndex.containsKey(item)) {
            throw new IllegalArgumentException("Item not found in heap");
        }
        remove(itemToIndex.get(item));
    }

    private void updatePriority(int index) {
        percolateUp(index);
        percolateDown(index);
    }

    public void updatePriority(T item) {
        if (!itemToIndex.containsKey(item)) {
            throw new IllegalArgumentException("Given item is not present in the priority queue!");
        }
        updatePriority(itemToIndex.get(item));
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return arr[0];
    }

    public List<T> toList() {
        List<T> copy = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            copy.add(i, arr[i]);
        }
        return copy;
    }

    // For debugging
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        String str = "[(" + arr[0] + " " + itemToIndex.get(arr[0]) + ")";
        for (int i = 1; i < size; i++) {
            str += ",(" + arr[i] + " " + itemToIndex.get(arr[i]) + ")";
        }
        return str + "]";
    }
}
