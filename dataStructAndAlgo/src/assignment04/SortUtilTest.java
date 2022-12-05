package assignment04;

import assignment04_test.QuickSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static assignment04_test.QuickSort.generateAverageCase;
import static org.junit.jupiter.api.Assertions.*;

class SortUtilTest {

    @Test
    void mergesort() {
        ArrayList<Integer> random = generateAverageCase(20);
        ArrayList<Integer> expected = new ArrayList<>(random);
        Collections.sort(expected);
        System.out.println("mergeSort");
        System.out.println("random"+random);
        System.out.println("expected"+expected);
        SortUtil.mergesort(random,Integer::compareTo);
        System.out.println("after sorting"+random);
        Assertions.assertArrayEquals(expected.toArray(new Integer[0]), random.toArray(new Integer[0]));

    }

    @Test
    void quicksort() {
        ArrayList<Integer> random = generateAverageCase(20);
        ArrayList<Integer> expected = new ArrayList<>(random);
        Collections.sort(expected);
        System.out.println("quickSort");
        System.out.println("random"+random);
        System.out.println("expected"+expected);
        QuickSort.quickSort(random,Integer::compareTo);
        System.out.println("after sorting"+random);
        Assertions.assertArrayEquals(expected.toArray(new Integer[0]), random.toArray(new Integer[0]));
    }
}