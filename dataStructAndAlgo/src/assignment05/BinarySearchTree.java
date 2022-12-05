package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {
    BinaryTreeNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    @Override
    public boolean add(T item) {
        try {
            root = add(item, root);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private BinaryTreeNode<T> add(T item, BinaryTreeNode<T> node) throws Exception {
        if (node == null) {
            node = new BinaryTreeNode<>(item);
            return node;
        }
        int cmp = item.compareTo(node.key);
        if (cmp == 0) {
            throw new Exception("Duplicated item");
        }
        if (cmp < 0) {
            node.left = add(item, node.left);
        } else {
            node.right = add(item, node.right);
        }
        return node;
    }

    @Override
    public boolean addAll(Collection<? extends T> items) {
        int count = 0;
        for (T item : items) {
            if (add(item)) {
                count++;
            }
        }
        return count > 0;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(T item) {
        return contains(item, root);
    }

    private boolean contains(T item, BinaryTreeNode<T> node) {
        if (node == null) {
            return false;
        }
        int cmp = item.compareTo(node.key);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return contains(item, node.left);
        } else {
            return contains(item, node.right);
        }
    }

    @Override
    public boolean containsAll(Collection<? extends T> items) {
        for (T item : items) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public T first() throws NoSuchElementException {
        BinaryTreeNode<T> currentNode = root;

        if (root == null) {
            throw new NoSuchElementException();
        } else {
            while (currentNode.left != null) {
                currentNode = currentNode.left;
            }
            return currentNode.key;
        }
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public T last() throws NoSuchElementException {
        BinaryTreeNode<T> currentNode = root;

        if (root == null) {
            throw new NoSuchElementException();
        } else {
            while (currentNode.right != null) {
                currentNode = currentNode.right;
            }
            return currentNode.key;
        }
    }

    boolean contains;
    @Override
    public boolean remove(T item) {
        contains = false;
        root=remove(root, item);
        return contains;
    }

    private BinaryTreeNode<T> remove(BinaryTreeNode<T> node, T key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right = remove(node.right, key);
        } else if (cmp < 0) {
            node.left = remove(node.left, key);
        } else {
            contains = true;
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            //in case that the node has both left and right subtrees, we can choose the minimum of the right
            // or maximum of the left to take the place of the node to be deleted
            BinaryTreeNode<T> minNode = node.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }
            node.key = minNode.key;
            node.right = remove(node.right, minNode.key);
        }
        return node;
    }

    @Override
    public boolean removeAll(Collection<? extends T> items) {
        int count = 0;
        for (T item : items) {
            if (remove(item)) {
                count++;
            }
        }
        return count > 0;
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.left) + size(node.right);
        }
    }

    ArrayList<T> list;

    @Override
    public ArrayList<T> toArrayList() {
        list = new ArrayList<>();
        addInOrder(root);
        return list;
    }

    private void addInOrder(BinaryTreeNode<T> node) {
        if (node == null) {
            return;
        }
        addInOrder(node.left);
        list.add(node.key);
        addInOrder(node.right);
    }
}
