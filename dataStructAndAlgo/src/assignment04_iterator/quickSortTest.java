package assignment04_iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static assignment04_iterator.QuickSort.generateAverageCase;

class quickSortTest {

    @Test
    void sort() {
        ArrayList<Integer> random = generateAverageCase(20);
        ArrayList<Integer> expected = new ArrayList<>(random);
        Collections.sort(expected);
        System.out.println(random);
        QuickSort.sort(random);
        System.out.println(random);
        Assertions.assertArrayEquals(expected.toArray(new Integer[0]), random.toArray(new Integer[0]));
//        Integer[] random = {12, 15, 4, 7, 2, 9, 3, 100};
//        Integer[] expected = {2, 3, 4, 7, 9, 12, 15, 100};
//        QuickSort.sort(random);
//        Assertions.assertArrayEquals(expected, random);
//        System.out.println(Arrays.toString(random));

    }

    @Test
    void sort2() {
        int size = 1 << 20;
        int[] actual = new int[size];
        int[] expected = new int[size];
        for (int i = 0; i < size; ++i) {
            actual[i] = i;
            expected[i] = i;
        }

        // TIME IT!
        long start = System.nanoTime();
//        QuickSort.sort(createList(actual));
        Collections.sort(createList(actual));
//        Arrays.sort(actual);
        Sort.sort(createList(actual));
        long stop = System.nanoTime();
        System.out.print(String.format("trial, %d, %d\n", size, (stop-start)));
        Assertions.assertArrayEquals(expected, actual);
    }

    ArrayList<Integer> createList(int[] array) {
        ArrayList<Integer> list = new ArrayList<>(array.length);
        for(int d : array) {
            list.add(d);
        }
        return list;
    }
}