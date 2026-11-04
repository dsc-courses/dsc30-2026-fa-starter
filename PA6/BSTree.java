import java.util.ArrayList;

/**
 * TODO
 *
 * @author TODO
 * @since TODO
 */

public class BSTree {

    private int nelems;
    private BSTNode root;

    protected class BSTNode {
        /* instance variables, feel free to add more if you need */

        String key;
        int keyCount;
        BSTNode left;
        BSTNode right;

        public BSTNode(BSTNode left, BSTNode right, String key) {
            // TODO
        }

        public String getKey() {
            // TODO
            return null;
        }

        public BSTNode getLeft() {
            // TODO
            return null;
        }

        public BSTNode getRight() {
            // TODO
            return null;
        }

        public int getNodeCount() {
            // TODO
            return 0;
        }

        public void setLeft(BSTNode newleft) {
            // TODO
        }

        public void setRight(BSTNode newright) {
            // TODO
        }
    }

    public BSTree() {
        // TODO
    }

    public BSTNode getRoot() {
        // TODO
        return null;
    }

    public int getSize() {
        // TODO
        return 0;
    }

    public boolean insert(String key) {
        // TODO
        return false;
    }

    public boolean findKey(String key) {
        // TODO
        return false;
    }

    public void inOrderTraversal(String[] keyArray) {
        // TODO
    }

    public int findHeight() {
        // TODO
        return 0;
    }

    public int leafCount() {
        // TODO
        return 0;
    }

    // Part 5 extra credit
    public ArrayList<String> intersection(BSTree tree1, BSTree tree2) {
        // TODO
        return null;
    }

    // Part 5 extra credit
    public String levelMax(int level) {
        // TODO
        return null;
    }

}
