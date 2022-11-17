package assignment03;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BinarySearchSetTest {
    //    ==== Stage 1 - Simple & Basic
//    ==== Stage 2 - Dynamic Sizing
//    ==== Stage 3 - Search
//    ==== Stage 4 - Remove
//    ==== Stage 5 - Iterate
//    ==== Stage 6 - Comparable Data Type
//    ==== Uncomment to test
    @Test
    public void testAdd() {
//        Let default size to 2
        BinarySearchSet<Integer> set = new BinarySearchSet<>();
        assertEquals(0, set.size());
//        1 - normal insert
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertNotEquals(0, set.size());
        assertEquals(2, set.size());
//        2 - insert more than capacity
        assertTrue(set.add(3));
        assertTrue(set.capacity > 2);
        assertEquals(3, set.size());
//        3 - insert duplicate value
        assertFalse(set.add(1));
        assertFalse(set.add(3));
    }

    @Test
    public void testSearch() {
        BinarySearchSet<Integer> set = new BinarySearchSet<>();
//        We don't need to test insert here
        set.add(1);
        set.add(2);
        set.add(3);
//        3 - search
        assertTrue(set.contains(2));
        assertFalse(set.contains(5));
//        out of order insert
        set.add(6);
        set.add(4);
        set.add(7);
        assertTrue(set.contains(4));
        assertTrue(set.contains(7));
        assertFalse(set.contains(9));
//        Note: we didn't check for order, assuming
    }

    @Test
    void addAll() {
        BinarySearchSet<Integer> set = new BinarySearchSet<>();
        Integer[] arr = {1, 4, 6, 3};
        List<Integer> list = Arrays.asList(arr);
        set.addAll(list);
//        Integer[] actual = set.toArray(); error
//        Object[] actual = set.toArray(); works
        Integer[] expected = {1, 3, 4, 6};
        assertArrayEquals(set.toArray(), expected);
    }

    @Test
    void containsAll() {
        BinarySearchSet<Integer> set = new BinarySearchSet<>();
        Integer[] arr = {1, 4, 6, 3, 10, 44, 65};
        List<Integer> list = Arrays.asList(arr);
        set.addAll(list);
        //test random
        Integer[] arr1 = {1, 4, 6, 3, 7};
        List<Integer> list1 = Arrays.asList(arr1);
        assertFalse(list.containsAll(list1));

        //test subset
        Integer[] arr2 = {1, 4, 3};
        List<Integer> list2 = Arrays.asList(arr2);
        assertTrue(list.containsAll(list2));

        //test duplicate
        Integer[] arr3 = {1, 4, 3, 4};
        List<Integer> list3 = Arrays.asList(arr3);
        assertTrue(list.containsAll(list3));

    }

    @Test
    void remove() {
        BinarySearchSet<Integer> set = new BinarySearchSet<>();
        assertFalse(set.remove(5));
        set.add(5);
        assertTrue(set.remove(5));

        Integer[] arr = {1, 4, 6, 3, 10, 44, 65};
        List<Integer> list = Arrays.asList(arr);
        set.addAll(list);

        //remove an existing element
        set.remove(3);
        Integer[] expected = {1, 4, 6, 10, 44, 65};
        assertArrayEquals(set.toArray(), expected);

        //remove a non-existing element
        assertFalse(set.remove(0));

        //the size after remove
        assertEquals(6, set.size());
    }

    @Test
    void removeAll() {
        BinarySearchSet<Integer> set = new BinarySearchSet<>();
        set.addAll(Arrays.asList(1, 4, 6, 3, 10, 44, 65));

        assertTrue(set.removeAll(Arrays.asList(1, 4, 6, 3, 7)));
        assertFalse(set.removeAll(Arrays.asList(2, 100, 300)));

        Integer[] arr ={10,44,65};
        assertArrayEquals(arr,set.toArray());
    }

    @Test
    void iterator() {
        BinarySearchSet<Integer> set = new BinarySearchSet<>();
        set.addAll(Arrays.asList(1, 4, 6, 3, 10, 44, 65));

        Iterator it = set.iterator();
        Integer[] arr = new Integer[set.size()];
        int i=0;
        while (it.hasNext()){
            arr[i]= (Integer) it.next();
            i++;
        }
        assertArrayEquals(set.toArray(),arr);
    }
}