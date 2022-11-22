package assignment04_iterator;

import java.util.ArrayList;
import java.util.ListIterator;

public class Sort {
    public static <T extends Comparable<? super T>> void sort(ArrayList<T> list) {
        Object[] arr = list.toArray();
        quicksort(arr);
        ListIterator<T> iterator = list.listIterator();
        for(Object item : arr) {
            iterator.next();
            iterator.set((T) item);
        }
    }
    public static void quicksort(Object[] arr) {
        quickSortIterative(arr, 0, arr.length-1);
    }

    static int partition(Object arr[], int low, int high) {
        Comparable pivot = (Comparable) arr[high];

        // index of smaller element
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than or equal to pivot
            if (((Comparable) arr[j]).compareTo(pivot) <= 0) {
                i++;

                // swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        swap(arr, i+1, high);
        return i + 1;
    }

    private static <T extends Comparable<? super T>> void swap(Object[] arr, int i, int j) {
        if (i == j) return;
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void quickSortIterative(Object[] arr, int l, int h) {
        // Create an auxiliary stack
        int[] stack = new int[h - l + 1];

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = l;
        stack[++top] = h;

        // Keep popping from stack while is not empty
        while (top >= 0) {
            // Pop h and l
            h = stack[top--];
            l = stack[top--];

            // Set pivot element at its correct position in sorted array
            int p = partition(arr, l, h);

            // If there are elements on left side of pivot, then push left side to stack
            if (p - 1 > l) {
                stack[++top] = l;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot, then push right side to stack
            if (p + 1 < h) {
                stack[++top] = p + 1;
                stack[++top] = h;
            }
        }
    }
}
