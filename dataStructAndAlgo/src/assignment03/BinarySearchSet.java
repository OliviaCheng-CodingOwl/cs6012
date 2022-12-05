package assignment03;

import java.util.*;
import java.util.function.Consumer;

@SuppressWarnings("unchecked")
public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {
    int capacity = 2;

    E[] data = (E[]) new Object[capacity];
    int last = 0;//the index of the first empty position in set
    Comparator<E> comparator_ = null;


    public BinarySearchSet() {
    }

    public BinarySearchSet(Comparator<? super E> comparator) {
        this.comparator_ = (Comparator<E>) comparator;
    }

    @Override
    public Comparator<? super E> comparator() {
        return this.comparator_;
    }

    @Override
    public E first() throws NoSuchElementException {
        if (this.data.length == 0 || last == 0) {
            throw new NoSuchElementException("data is empty");
        }
        return this.data[0];
    }

    @Override
    public E last() throws NoSuchElementException {
        if (this.data.length == 0 || last == 0) {
            throw new NoSuchElementException("data is empty");
        }
        return this.data[last - 1];
    }

    /**
     * 
     * @return true if data is full, false otherwise
     */
    private boolean isFull() {
        return last == capacity;
    }

    /**
     * Double the size data
     */
    private void doubleSize() {
        capacity = capacity * 2;
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < last; ++i) {
            newData[i] = this.data[i];
        }
        this.data = newData;
    }

    /**
     * Shift elements in data to the right
     * @param index
     */
    private void rightShift(int index) {
        for (int i = last; i > index; i--) {
            data[i] = data[i - 1];
        }
    }

    @Override
    public boolean add(E element) {
        if (isEmpty()) {
            this.data[last] = element;
            ++last;
            return true;
        }

        int index = binarySearch(this.data, 0, last, element);
        if (index >= 0) {
            return false;
        }
//        check capacity and double if needed
        if (isFull()) {
            doubleSize();
        }
        index = -index - 1;
        if (index < last) {
            rightShift(index);
        }
        this.data[index] = element;
        ++last;
        return true;
    }

    /**
     * 
     * @param array
     * @param fromIndex
     * @param toIndex
     * @param key
     * @return
     */
    private int binarySearch(E[] array, int fromIndex, int toIndex, E key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            //cmp<0, if a[mid]<key; cmp>0, if a[mid]>key; cmp=0, if a[mid]=key;
            int cmp = 0;
            //this.comparator_ = null means the array is sorted in natural order, so it has default compareTo method
            //this.comparator_ != null means the array is sorted by a self-defined comparator, so we use that comparator instead
            if (this.comparator_ != null) {
                cmp = this.comparator_.compare(array[mid], key);
            } else {
                Comparable midVal = (Comparable) array[mid];
                cmp = midVal.compareTo(key);
            }

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }

        return -low - 1;  // key not found.
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        int count = 0;
        for (E e : elements) {
            if (add(e)) {
                count++;
            }
        }
        return count > 0;
    }

    @Override
    public void clear() {
        data = null;
        capacity = 0;
        comparator_ = null;
        last = 0;
    }

    @Override
    public boolean contains(E element) {
        int index = binarySearch(this.data, 0, last, element);
        return index >= 0;
    }

    @Override
    public boolean containsAll(Collection<? extends E> elements) {

        for (E e : elements) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return last == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<>(this);
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public boolean remove(E element) {
        if (contains(element) == false) {
            return false;
        }

        int index = binarySearch(data, 0, last, element);
        leftShift(index);
        data[last - 1] = null;
        last--;
        return true;
    }

    private void leftShift(int index) {
        for (int i = index; i < last - 1; i++) {
            data[i] = data[i + 1];
        }
    }

    @Override
    public boolean removeAll(Collection<? extends E> elements) {
        int count = 0;
        for (E e : elements) {
            if (contains(e)) {
                remove(e);
                count++;
            }
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return last;
    }

    @Override
    public E[] toArray() {
        E[] dataCopy = (E[]) new Object[size()];
        for (int i = 0; i < size(); i++) {
            dataCopy[i] = data[i];
        }
        return dataCopy;
    }
}

class CustomIterator<E> implements Iterator<E> {
    int current = 0;
    int last;
    E[] data;

    // constructor
    CustomIterator(BinarySearchSet<E> set) {
        // initialize cursor
        last = set.last;
        data = set.data;
    }

    // Checks if the next element exists
    public boolean hasNext() {
        return current < last;
    }

    // moves the cursor/iterator to next element
    public E next() {
        return data[current++];
    }

    // Used to remove an element. Implement only if needed
//    public void remove() {
//        // Default throws UnsupportedOperationException.
//    }
}
