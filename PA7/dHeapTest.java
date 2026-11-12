/*
 * Name: TODO
 * PID: TODO
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Starter tests for dHeap.
 * Add tests for every public constructor and method as specified in Part 2.
 * Include both min-heaps and max-heaps, different branching factors,
 * resizing, duplicate priorities, and all specified exceptions.
 * Style is not graded for this file.
 */
public class dHeapTest {
    private dHeap maxHeap;
    private dHeap minHeap;

    @BeforeEach
    public void setUp() {
        maxHeap = new dHeap();
        minHeap = new dHeap(4, 10, false);
    }

    @Test
    public void testInitialSize() {
        assertEquals(0, maxHeap.size());
        assertEquals(0, minHeap.size());
    }

    @Test
    public void testAddAndElement() {
        HeapEntry entry = new HeapEntry(2.5f, 42);
        maxHeap.add(entry);

        assertEquals(1, maxHeap.size());
        assertSame(entry, maxHeap.element());
    }

    // TODO: Write the remaining tests required by Part 2.
}
