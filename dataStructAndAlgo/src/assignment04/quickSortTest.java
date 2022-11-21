package assignment04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static assignment04.QuickSort.generateAverageCase;

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
}