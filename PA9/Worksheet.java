import java.util.ArrayList;
import java.util.List;

/** PA9 Part 1 worksheet. Hardcode each answer in its method below. */
public class Worksheet {
    /** Q1.1(a) Huffman leaves, left to right within each level from bottom to top. */
    public static ArrayList<HuffmanNode> q1aEncodeChar() {
        // TODO: Replace this empty result with your answer.
        return new ArrayList<>();
    }

    /** Q1.1(b) Huffman leaves, left to right within each level from bottom to top. */
    public static ArrayList<HuffmanNode> q1bEncodeChar() {
        // TODO: Replace this empty result with your answer.
        return new ArrayList<>();
    }

    /** Q1.2 B-tree nodes and their children, left to right within each level from bottom to top. */
    public static ArrayList<BNode> q2EncodeTree() {
        // TODO: Replace this empty result with your answer.
        return new ArrayList<>();
    }

    /** Q1.3 Graph 1 adjacency list, with rows and neighbors in alphabetical order. */
    public static String[][] q3aAdjacencyList() {
        // TODO: Replace this empty result with your answer.
        return new String[0][0];
    }

    /** Q1.3 Graph 1 BFS visit order starting at A. */
    public static String[] q3aBfs() {
        // TODO: Replace this empty result with your answer.
        return new String[0];
    }

    /** Q1.3 Graph 1 DFS visit order starting at A. */
    public static String[] q3aDfs() {
        // TODO: Replace this empty result with your answer.
        return new String[0];
    }

    /** Q1.3 Graph 2 adjacency list, with rows and neighbors in alphabetical order. */
    public static String[][] q3bAdjacencyList() {
        // TODO: Replace this empty result with your answer.
        return new String[0][0];
    }

    /** Q1.3 Graph 2 BFS visit order starting at I. */
    public static String[] q3bBfs() {
        // TODO: Replace this empty result with your answer.
        return new String[0];
    }

    /** Q1.3 Graph 2 DFS visit order starting at G. */
    public static String[] q3bDfs() {
        // TODO: Replace this empty result with your answer.
        return new String[0];
    }

    /** Prints each B-tree node and the data stored in its children. */
    public static void main(String[] args) {
        for (BNode node : q2EncodeTree()) {
            ArrayList<List<Integer>> childData = new ArrayList<>();
            for (BNode child : node.children) {
                childData.add(child.data);
            }
            System.out.println(node.data + " -> " + childData);
        }
    }
}

/** A leaf of a Huffman tree. */
class HuffmanNode {
    public final char c;
    public final int freq;
    public final String path;

    public HuffmanNode(char c, int freq, String path) {
        this.c = c;
        this.freq = freq;
        this.path = path;
    }
}

/** A B-tree node with its keys and children in left-to-right order. */
class BNode {
    public final ArrayList<Integer> data;
    public final ArrayList<BNode> children;

    public BNode(List<Integer> data) {
        this.data = new ArrayList<>(data);
        this.children = new ArrayList<>();
    }

    public void addChildren(BNode child) {
        children.add(child);
    }
}
