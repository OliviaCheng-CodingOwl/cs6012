package assignment05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class BinarySearchTreeTest {

    @Test
    void add() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        Assertions.assertTrue(tree.add("aa"));
        tree.add("bb");
        tree.add("cc");
        Assertions.assertFalse(tree.add("aa"));
        Assertions.assertFalse(tree.add("bb"));
        Assertions.assertFalse(tree.add("cc"));

    }

    @Test
    void addAll() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> wordsToAdd = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        Assertions.assertTrue(tree.addAll(wordsToAdd));
        //all the words are contained in the tree
        Assertions.assertFalse(tree.addAll(wordsToAdd));
    }

    @Test
    void clear() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> wordsToAdd = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        tree.addAll(wordsToAdd);
        tree.clear();
        Assertions.assertNull(tree.root);
        Assertions.assertEquals(0, tree.size());
    }

    @Test
    void contains() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        Assertions.assertFalse(tree.contains("aa"));
        tree.add("aa");
        Assertions.assertTrue(tree.contains("aa"));
        tree.add("bb");
        tree.add("cc");
        Assertions.assertTrue(tree.contains("cc"));
    }

    @Test
    void containsAll() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> wordsToAdd = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        tree.addAll(wordsToAdd);
        Assertions.assertTrue(tree.containsAll(wordsToAdd));

        List<String> wordsToAdd2 = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff", "gg");
        Assertions.assertFalse(tree.containsAll(wordsToAdd2));
    }

    @Test
    void first() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> wordsToAdd = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        tree.addAll(wordsToAdd);
        Assertions.assertEquals("aa", tree.first());
    }

    @Test
    void isEmpty() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> wordsToAdd = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        tree.addAll(wordsToAdd);
        Assertions.assertFalse(tree.isEmpty());
        tree.clear();
        Assertions.assertTrue(tree.isEmpty());
    }

    @Test
    void last() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> wordsToAdd = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        tree.addAll(wordsToAdd);
        Assertions.assertEquals("ff", tree.last());
    }

    @Test
    void remove() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> wordsToAdd = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        tree.addAll(wordsToAdd);
        tree.remove("aa");
        String[] expected = {"bb", "cc", "dd", "ee", "ff"};
        Assertions.assertArrayEquals(expected,tree.toArrayList().toArray());
    }

    @Test
    void removeAll() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> wordsToAdd = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        tree.addAll(wordsToAdd);
        List<String> wordsToRemove = Arrays.asList( "bb", "cc", "dd", "ee", "ff");
        tree.removeAll(wordsToRemove);
        String[] expected = {"aa"};
        Assertions.assertArrayEquals(expected,tree.toArrayList().toArray());

    }

    @Test
    void size() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        List<String> wordsToAdd = Arrays.asList("aa", "bb", "cc", "dd", "ee", "ff");
        tree.addAll(wordsToAdd);
        Assertions.assertEquals(6, tree.size());
    }


    @Test
    void toArrayList() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        String[] wordsToAdd = {"dd", "bb", "ee", "ff", "aa", "cc"};
        String[] expected = {"aa", "bb", "cc", "dd", "ee", "ff"};
        tree.addAll(List.of(wordsToAdd));
        Assertions.assertArrayEquals(expected, tree.toArrayList().toArray());
    }

}

