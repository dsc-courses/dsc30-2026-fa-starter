import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/** Expand this file for Part 2. The one example below is not sufficient.
 * Include at least three assertions per sort and separate test methods for
 * different behaviors. Cover unsorted/sorted/reverse input, duplicates,
 * negative values, singleton/empty arrays and partial ranges.
 */
class SortsTest {
    @Test
    void insertionSortExample() {
        int[] actual = {3, 1, 2};
        Sorts.insertionSort(actual, 0, actual.length - 1);
        assertArrayEquals(new int[] {1, 2, 3}, actual);
    }

    // TODO: Add your own insertion, selection, quick and merge sort tests.
}
