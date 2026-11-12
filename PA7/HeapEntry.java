/**
 * An entry with a numeric priority and an associated value.
 * Provided code for PA7.
 */
public class HeapEntry {
    private final float priority;
    private final int value;

    /**
     * Creates an entry.
     *
     * @param priority the priority used to order this entry
     * @param value the value associated with this entry
     */
    public HeapEntry(float priority, int value) {
        this.priority = priority;
        this.value = value;
    }

    /**
     * Returns the priority used to order this entry.
     *
     * @return the priority
     */
    public float getPriority() {
        return priority;
    }

    /**
     * Returns the associated value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
