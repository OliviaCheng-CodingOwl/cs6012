package assignment04_test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class QuickSort {

    private static <T> boolean less(T v, T w, Comparator comparator) {
        if (comparator.compare(v, w) < 0) {
            return true;
        } else {
            return false;
        }
    }

    private static <T> void swap(ArrayList<T> a, int i, int j) {
        if (i == j) return;
        T t = a.get(i);
        a.set(i, a.get(j));
        a.set(j, t);
    }

    public static <T> void quickSort(ArrayList<T> list, Comparator<? super T> comparator) {
        int lo = 0;
        int hi = list.size() - 1;
        quickSort(list, lo, hi, comparator);
    }

    public static <T> void quickSort(ArrayList<T> list, int lo, int hi, Comparator comparator) {
        if (hi <= lo) {
            return;
        }
//        int partition = partition(list, lo, hi, comparator);
//        int partition = partition2(list, lo, hi, comparator);
        int partition = partition3(list, lo, hi, comparator);
        quickSort(list, lo, partition - 1, comparator);
        quickSort(list, partition + 1, hi, comparator);
    }

    //ues a random element in list as pivot
    public static <T> int partition2(ArrayList<T> list, int lo, int hi, Comparator comparator) {
        int pivot = lo + (int) Math.random() * (hi - lo + 1);
        swap(list, lo, pivot);
        return partition(list, lo, hi, comparator);
    }

    //use the middle element in list as pivot
    public static <T> int partition3(ArrayList<T> list, int lo, int hi, Comparator comparator) {
        int pivot = lo + (hi-lo)/2;
        swap(list, lo, pivot);
        return partition(list, lo, hi, comparator);
    }

    //use the first element in list as pivot
    public static <T> int partition(ArrayList<T> list, int lo, int hi, Comparator comparator) {
        T pivot = list.get(lo);
        int left = lo;
        int right = hi + 1;

        while (true) {
            /**
             * from right to left
             */
            while (less(pivot, list.get(--right), comparator)) {
                if (right == lo) {
                    break;
                }
            }
            /**
             * from left to right
             */
            while (less(list.get(++left), pivot, comparator)) {
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
