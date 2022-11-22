package assignment04_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static assignment04_test.QuickSort.generateAverageCase;

class QuickSortTest {

    @Test
    void sort() {
        ArrayList<Integer> random = generateAverageCase(20);
        ArrayList<Integer> expected = new ArrayList<>(random);
        Collections.sort(expected);
        System.out.println("random"+random);
        System.out.println("expected"+expected);
        QuickSort.quickSort(random,Integer::compareTo);
        System.out.println("after sorting"+random);
        Assertions.assertArrayEquals(expected.toArray(new Integer[0]), random.toArray(new Integer[0]));

    }
}