/**
 * TODO
 *
 * @author TODO
 * @since TODO
 */

public class orderStatTree {

    private int nelems;
    private orderStatNode root;

    protected class orderStatNode {
        /* instance variables, feel free to add more if you need */

        String key;
        int keyCount;
        int subtreeSize;
        orderStatNode left;
        orderStatNode right;

        public orderStatNode(orderStatNode left, orderStatNode right, String key) {
            // TODO
        }

        public String getKey() {
            // TODO
            return null;
        }

        public orderStatNode getLeft() {
            // TODO
            return null;
        }

        public orderStatNode getRight() {
            // TODO
            return null;
        }

        public int getNodeCount() {
            // TODO
            return 0;
        }

        public void setLeft(orderStatNode newleft) {
            // TODO
        }

        public void setRight(orderStatNode newright) {
            // TODO
        }
    }

    public orderStatTree() {
        // TODO
    }

    public orderStatNode getRoot() {
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

    public int getSubtreeSize(String key) {
        // TODO
        return 0;
    }

    public String getPercentile(int percentile) {
        // TODO
        return null;
    }

    public String median() {
        // TODO
        return null;
    }

    public String min() {
        // TODO
        return null;
    }

    public String max() {
        // TODO
        return null;
    }

}
