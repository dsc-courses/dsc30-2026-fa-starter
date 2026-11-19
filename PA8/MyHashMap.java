/*
 * Name: TODO
 * PID: TODO
 */

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * TODO: Add your class description here
 *
 * @author TODO
 * @since TODO
 */
public class MyHashMap {

    private LinkedList<Entry>[] table;
    private int size;
    private int numCollisions;
    private int numRehashes;
    private String statsLog;

    /**
     * A key and its value, stored in one bucket of the table.
     */
    protected class Entry {

        String key;
        int value;

        /**
         * Create an entry with the given key and value.
         *
         * @param key   Key of the entry
         * @param value Value of the entry
         */
        public Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Return the key.
         *
         * @return The key
         */
        public String getKey() {
            return key;
        }

        /**
         * Return the value.
         *
         * @return The value
         */
        public int getValue() {
            return value;
        }

        /**
         * Replace the value.
         *
         * @param value New value
         */
        public void setValue(int value) {
            this.value = value;
        }
    }

    public MyHashMap() {
        // TODO
    }

    @SuppressWarnings("unchecked")
    public MyHashMap(int capacity) {
        // TODO
    }

    public boolean insert(String key, int value) {
        // TODO
        return false;
    }

    public boolean update(String key, int value) {
        // TODO
        return false;
    }

    public boolean delete(String key) {
        // TODO
        return false;
    }

    public boolean lookup(String key) {
        // TODO
        return false;
    }

    public int get(String key) {
        // TODO
        return 0;
    }

    public String[] returnAll() {
        // TODO
        return null;
    }

    public int size() {
        // TODO
        return 0;
    }

    public int capacity() {
        // TODO
        return 0;
    }

    public String getStatsLog() {
        // TODO
        return null;
    }

    /**
     * Print every bucket on its own line as key=value pairs. Provided to help
     * with debugging.
     */
    public void printTable() {
        String s = "";
        for (int i = 0; i < table.length; i++) {
            s = s + i + ":";
            if (!table[i].isEmpty()) {
                for (Entry e : table[i]) {
                    s = s + " " + e.getKey() + "=" + e.getValue() + ",";
                }
                s = s.substring(0, s.length() - 1);
            }
            s = s + "\n";
        }
        s = s.substring(0, s.length() - 1);
        System.out.println(s);
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        // TODO
    }

    private int hashString(String key) {
        // TODO
        return 0;
    }
}
