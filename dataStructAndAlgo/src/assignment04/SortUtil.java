package assignment04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SortUtil {
    private static int mergeSortThreshold = 20;

    //// MERGE SORT ////

    // Merge sort driver
    public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator){

        ArrayList<T> tempList = new ArrayList<T>(list.size());

        //Creating a temporary new array list with blank data
        for (int i = 0; i < list.size(); i++)
            tempList.add(null);

        //Sending the Actual List, the temp list, the start index, and last index.
        mergeSort(list, tempList, 0, list.size() - 1, comparator);

    }

    private static <T> void mergeSort(ArrayList<T> list, ArrayList<T> tempList, int start, int end, Comparator<? super T> comparator) {
        if (start < end) {

            // if the size of the new portion of the list is less than the threshold use the insertion sort
            // if not continue with merge sort  and get the middle index to get 2 new portions.
            if (end - start < mergeSortThreshold)
                insertionSort(list, start, end, comparator);
            else {
                // Getting the middle index of the list
                int middle = (start + end) / 2;

                //We defined the left and right side of the new part
                //by using the start, middle, and end index
                //sending Left side of the List
                mergeSort(list, tempList, start, middle, comparator);
                //sending Right side of the List
                mergeSort(list, tempList, middle + 1, end, comparator);

                merge(list, tempList, start, end, comparator);
            }

        }
    }

    private static <T> void merge(ArrayList<T> list, ArrayList<T> tempList, int start, int end, Comparator<? super T> comparator) {

        int numberElements = end - start + 1;    // Getting the number of elements on list
        int tempPosition = start;                // Saving the temp position on a variable
        int middle = (start + end) / 2 + 1;      // Getting the middle index of the new part
        int right = middle - 1;                  // Getting the start index for right side

        //
        while (start <= right && middle <= end) {
            int  res = comparator.compare(list.get(start), list.get(middle));
            if (res <= 0) {
                tempList.set(tempPosition++, list.get(start++));
            } else {
                tempList.set(tempPosition++, list.get(middle++));
            }
        }

        // Setting value to list if start
        while (start <= right) {
            tempList.set(tempPosition++, list.get(start++));
        }

        //
        while (middle <= end) {
            tempList.set(tempPosition++, list.get(middle++));
        }

        //
        for (int i = 0; i < numberElements; i++, end--) {
            list.set(end, tempList.get(end));
        }

    }

    private static <T> void insertionSort(ArrayList<T> list, int start, int end, Comparator<? super T> comparator) {
        int j;
        for (int i = start; i <= end; i++) {
            T value = list.get(i);

            for (j = i; j > start && comparator.compare(value, list.get(j - 1)) < 0; j--) {
                list.set(j, list.get(j - 1));
            }
            list.set(j, value);
        }
    }


    //// QUICK SORT ////

    public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator){
        int lo = 0;
        int hi = list.size() - 1;
        quickSort(list, lo, hi,comparator);
    }

    public static <T> void quickSort(ArrayList<T> list, int lo, int hi, Comparator comparator) {
        if (hi <= lo) {
            return;
        }
//        int partition = partition(list, lo, hi, comparator);//use the first element in list as pivot
//        int partition = partition2(list, lo, hi, comparator);//ues a random element in list as pivot
        int partition = partition3(list, lo, hi, comparator);//use the middle element in list as pivot
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

    private static <T> boolean less(T v, T w, Comparator comparator) {
        if (comparator.compare(v,w)<0){
            return true;
        }else {
            return false;
        }
    }

    private static <T> void swap(ArrayList<T> a, int i, int j) {
        T t = a.get(i);
        a.set(i, a.get(j));
        a.set(j, t);
    }


    /**
     * generate cases:
     * Best Case, Average Case, Worst Case
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
