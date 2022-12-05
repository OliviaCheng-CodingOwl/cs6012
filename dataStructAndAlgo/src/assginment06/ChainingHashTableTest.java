package assginment06;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ChainingHashTableTest {
    @Test
    void add() {
        ChainingHashTable bad = new ChainingHashTable(10, new BadHashFunctor());
        bad.add("Test");
        Assertions.assertEquals(1, bad.size());
    }

    @Test
    void addAll() {
        ChainingHashTable bad = new ChainingHashTable(10, new BadHashFunctor());
        String[] wordsToAdd = new String[]{"this", "is", "the", "test", "for", "addAll"};
        bad.addAll(List.of(wordsToAdd));
        Assertions.assertEquals(6, bad.size());
    }

    @Test
    void clear() {
        ChainingHashTable bad = new ChainingHashTable(10, new BadHashFunctor());
        String[] wordsToAdd = new String[]{"this", "is", "the", "test", "for", "clear"};
        bad.addAll(List.of(wordsToAdd));
        bad.clear();
        Assertions.assertEquals(0, bad.size());
    }

    @Test
    void contains() {
        ChainingHashTable bad = new ChainingHashTable(10, new BadHashFunctor());
        String[] wordsToAdd = new String[]{"this", "is", "the", "test", "for", "contains"};
        bad.addAll(List.of(wordsToAdd));
        Assertions.assertTrue(bad.contains("contains"));
    }

    @Test
    void containsAll() {
        ChainingHashTable bad = new ChainingHashTable(10, new BadHashFunctor());
        String[] wordsToAdd = new String[]{"this", "is", "the", "test", "for", "containsAll"};
        bad.addAll(List.of(wordsToAdd));
        Assertions.assertTrue(bad.containsAll(List.of(wordsToAdd)));
    }

    @Test
    void isEmpty() {
        ChainingHashTable bad = new ChainingHashTable(10, new BadHashFunctor());
        String[] wordsToAdd = new String[]{"this", "is", "the", "test", "for", "isEmpty"};
        bad.addAll(List.of(wordsToAdd));
        bad.clear();
        Assertions.assertTrue(bad.isEmpty());
    }

    @Test
    void remove() {
        ChainingHashTable bad = new ChainingHashTable(10, new BadHashFunctor());
        String[] wordsToAdd = new String[]{"this", "is", "the", "test", "for", "remove"};
        bad.addAll(List.of(wordsToAdd));
        bad.remove("remove");
        Assertions.assertFalse(bad.contains("remove"));
    }

    @Test
    void removeAll() {
        ChainingHashTable bad = new ChainingHashTable(10, new BadHashFunctor());
        String[] wordsToAdd = new String[]{"this", "is", "the", "test", "for", "removeAll"};
        bad.addAll(List.of(wordsToAdd));
        bad.removeAll(List.of(wordsToAdd));
        Assertions.assertEquals(0, bad.size());
    }

    @Test
    void size() {
        ChainingHashTable bad = new ChainingHashTable(10, new BadHashFunctor());
        String[] wordsToAdd = new String[]{"this", "is", "the", "test", "for", "size"};
        bad.addAll(List.of(wordsToAdd));
        Assertions.assertEquals(6, bad.size());
    }

    @Test
    void collisionNum() {
        ChainingHashTable bad = new ChainingHashTable(10, new BadHashFunctor());
        ChainingHashTable mediocre = new ChainingHashTable(10, new MediocreHashFunctor());
        ChainingHashTable good = new ChainingHashTable(10, new GoodHashFunctor());

        String[] wordsToAdd = new String[]{"abc", "acb"};
        bad.addAll(List.of(wordsToAdd));
        mediocre.addAll(List.of(wordsToAdd));
        good.addAll(List.of(wordsToAdd));

        Assertions.assertEquals(1,bad.collisionNum());
        Assertions.assertEquals(1,mediocre.collisionNum());
        Assertions.assertEquals(0,good.collisionNum());
    }
}