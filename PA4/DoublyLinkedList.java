/*
 * NAME: TODO
 * PID: TODO
 */

import java.util.AbstractList;

/**
 * TODO
 *
 * @author TODO
 * @since TODO
 */
public class DoublyLinkedList extends AbstractList<String> {

    /* DLL instance variables */
    private int size;
    private Node head;
    private Node tail;

    /**
     * TODO
     */
    protected class Node {

        /* Node instance variables */
        String elem;
        Node next;
        Node prev;

        /**
         * TODO
         */
        private Node(String element) {
            // TODO: complete constructor
        }

        /**
         * TODO
         */
        private Node(String element, Node nextNode, Node prevNode) {
            // TODO: complete implementation
        }

        /**
         * TODO
         */
        public void setElement(String element) {
            // TODO: complete implementation
        }

        /**
         * TODO
         */
        public String getElement() {
            // TODO: complete implementation
            return null;
        }

        /**
         * TODO
         */
        public void setNext(Node n) {
            // TODO: complete implementation
        }

        /**
         * TODO
         */
        public Node getNext() {
            // TODO: complete implementation
            return null;
        }

        /**
         * TODO
         */
        public void setPrev(Node p) {
            // TODO: complete implementation
        }

        /**
         * TODO
         */
        public Node getPrev() {
            // TODO: complete implementation
            return null;
        }

        /**
         * TODO
         */
        public void remove() {
            // TODO: complete implementation
        }
    }

    /**
     * TODO
     */
    public DoublyLinkedList() {
        // TODO: complete default constructor
    }

    /**
     * TODO
     */
    @Override
    public boolean add(String element) throws NullPointerException {
        // TODO: complete implementation
        return true;
    }

    /**
     * TODO
     */
    @Override
    public void add(int index, String element)
            throws IndexOutOfBoundsException, NullPointerException {
        // TODO: complete implementation
    }

    /**
     * TODO
     */
    @Override
    public void clear() {
        // TODO: complete implementation
    }

    /**
     * TODO
     */
    @Override
    public boolean contains(Object element) {
        // TODO: complete implementation
        return false;
    }

    /**
     * TODO
     */
    @Override
    public String get(int index) throws IndexOutOfBoundsException {
        // TODO: complete implementation
        return null;
    }

    /**
     * TODO
     */
    private Node getNth(int index) {
        // TODO: complete implementation
        return null;
    }

    /**
     * TODO
     */
    @Override
    public boolean isEmpty() {
        // TODO: complete implementation
        return true;
    }

    /**
     * TODO
     */
    @Override
    public String remove(int index) throws IndexOutOfBoundsException {
        // TODO: complete implementation
        return null;
    }

    /**
     * TODO
     */
    @Override
    public String set(int index, String element)
            throws IndexOutOfBoundsException, NullPointerException {
        // TODO: complete implementation
        return null;
    }

    /**
     * TODO
     */
    @Override
    public int size() {
        // TODO: complete implementation
        return 0;
    }

    /**
     * TODO
     */
    @Override
    public String toString() {
        // TODO: complete implementation
        return null;
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * TODO
     */
    public void slice(int start, int end, int step) {
        // TODO: complete implementation
    }

    /**
     * TODO
     */
    public void swapHalves(int n, int start, int end) {
        // TODO: complete implementation
    }

}
