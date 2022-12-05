package assginment06;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {

    //total number of buckets
    private final int capacity;
    private final HashFunctor functor;
    private final LinkedList<String>[] storage;


    //total number of the items
    private int size = 0;

    public ChainingHashTable(int capacity, HashFunctor functor) {
        this.capacity = capacity;
        this.functor = functor;
//        create an array of LinkedList
        this.storage = (LinkedList<String>[]) new LinkedList[capacity];
        init();
    }

    /**
     * Initialize all the LinkedList in the array storage
     */
    private void init() {
        for (int i = 0; i < capacity; ++i) {
            storage[i] = new LinkedList<>();
        }
    }

    //time complexity O(1)
    private int getIndex(String item) {
        if (item == null) {
            throw new NullPointerException("item is null");
        } else {
            int hashValue = this.functor.hash(item);
            return hashValue % capacity;
        }
    }

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(String item) {
        try {
            int index = getIndex(item);
            LinkedList<String> bucket = storage[index];
            if (bucket.contains(item)) {
//            list.contains is O(n) for n is the size of the list
                return false;
            }
            bucket.add(item);
            ++size;
            return false;

        } catch (Exception e) {
            System.out.println("item is null");
            return false;
        }
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends String> items) {
        int count = 0;
        for (String item : items) {
            if (add(item)) {
                count++;
            }
        }
        return count > 0;
    }

    @Override
    public void clear() {
        size = 0;
        init();
    }

    @Override
    public boolean contains(String item) {
        try {
            int index = getIndex(item);
            return storage[index].contains(item);
        } catch (Exception e) {
            System.out.println("item is null");
            return false;
        }

    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */

    @Override
    public boolean containsAll(Collection<? extends String> items) {
        for (String item : items) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(String item) {
        try {
            int index = getIndex(item);
            LinkedList<String> bucket = storage[index];
            if (bucket.contains(item)) {
                bucket.remove(item);
                size--;
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("item is null");
            return false;
        }
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     */
    @Override
    public boolean removeAll(Collection<? extends String> items) {
        int count = 0;
        for (String item : items) {
            if (remove(item)) {
                count++;
            }
        }
        return count > 0;
    }


    @Override
    public int size() {
        return this.size;
    }

    /**
     *
     * @return the number of collision of the hashTable
     */
    public int collisionNum() {
        int num = 0;
        for (LinkedList bucket : storage) {
            if (bucket.size() > 0)
                num = num + bucket.size() - 1;
        }
        return num;
    }
}
