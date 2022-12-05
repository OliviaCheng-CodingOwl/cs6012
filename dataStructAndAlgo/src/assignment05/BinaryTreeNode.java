package assignment05;

public class BinaryTreeNode<T extends Comparable<? super T>> {
    T key;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;

    public BinaryTreeNode(T data) {
        key = data;
        left = null;
        right = null;
    }
}
