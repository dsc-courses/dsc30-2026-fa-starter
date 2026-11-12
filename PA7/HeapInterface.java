import java.util.NoSuchElementException;

/**
 * Operations for a heap of HeapEntry objects.
 * Provided code for PA7.
 */
public interface HeapInterface {
    /**
     * Returns the number of entries stored in the heap.
     *
     * @return the number of entries
     */
    int size();

    /**
     * Removes and returns the root entry.
     *
     * @return the root entry
     * @throws NoSuchElementException if the heap is empty
     */
    HeapEntry remove();

    /**
     * Adds an entry, resizing the backing array when necessary.
     *
     * @param entry the entry to add
     * @throws NullPointerException if entry is null
     */
    void add(HeapEntry entry);

    /**
     * Removes all entries from the heap.
     */
    void clear();

    /**
     * Returns the root entry without removing it.
     *
     * @return the root entry
     * @throws NoSuchElementException if the heap is empty
     */
    HeapEntry element();
}
