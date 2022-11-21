package assignment04;

import java.util.*;

public class QuickSort {

    private static <T extends Comparable<? super T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private static <T extends Comparable<? super T>> void swap(ArrayList<T> a, int i, int j) {
        T t = a.get(i);
        a.set(i, a.get(j));
        a.set(j, t);
    }

    public static <T extends Comparable<? super T>> void sort(ArrayList<T> list) {
        int lo = 0;
        int hi = list.size() - 1;
        sort(list, lo, hi);
    }

    public static <T extends Comparable<? super T>> void sort(ArrayList<T> list, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int partition = partition(list, lo, hi);
        sort(list, lo, partition - 1);
        sort(list, partition + 1, hi);
    }

    public static <T extends Comparable<? super T>> int partition(ArrayList<T> list, int lo, int hi) {
        T pivot = list.get(lo);
        int left = lo;
        int right = hi + 1;

        while (true) {
            /**
             * from right to left
             */
            while (less(pivot, list.get(--right))) {
                if (right == lo) {
                    break;
                }
            }

            /**
             * from left to right
             */
            while (less(list.get(++left), pivot)) {
                if (left == hi) {
                    break;
                }
            }

            if (left >= right) {
                break;
            } else {
                swap(list, left, right);
            }
        }

        swap(list, lo, right);
        return right;
    }

    /**
     * generate cases
     */

    public static ArrayList<Integer> generateBestCase(int size) {
        ArrayList<Integer> best = generateAverageCase(size);
        Collections.sort(best);
        return best;
    }

    public static ArrayList<Integer> generateAverageCase(int size) {
        ArrayList<Integer> average = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            average.add(i, random.nextInt(size));
        }
        return average;
    }

    public static ArrayList<Integer> generateWorstCase(int size) {
        ArrayList<Integer> worst = generateBestCase(size);
        Collections.reverse(worst);
        return worst;
    }


}
