/** Four in-place sorts of int arrays. Bounds are inclusive.
 * Call sort(a, 0, a.length - 1) to sort a whole array, including an empty one.
 * For a nonempty range require 0 <= start <= end < a.length.
 * An empty range has start == end + 1 with 0 <= start <= a.length.
 * Null arrays are not supported. Elements outside the range must not change.
 * Negative values, duplicates, empty arrays and singleton arrays are supported.
 */
public class Sorts {
    public static void insertionSort(int[] array, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= start && array[j] > key) {
                // TODO: Shift the element at j one position to the right.
                unfinished("GAP_INSERTION");
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void selectionSort(int[] array, int start, int end) {
        for (int i = start; i < end; i++) {
            int minimum = i;
            for (int j = i + 1; j <= end; j++) {
                // TODO: Update minimum if array[j] is smaller.
                unfinished("selection minimum");
            }
            swap(array, i, minimum);
        }
    }

    /** Part 3.1: complete the TODO in inPlacePartition; the recursive driver
     * and swap helper are provided. Keep the first-element pivot for submission.
     * The complete partition reference for Part 1 is in TRACING_REFERENCE.md.
     */
    public static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int pivotIndex = inPlacePartition(array, start, end, start);
            // TRACE HERE: after partition, before either recursive call.
            quickSort(array, start, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, end);
        }
    }

    // The first element is the pivot in the required quickSort implementation.
    // Moving a chosen pivot to stop preserves the older PA5 partition behavior.
    private static int inPlacePartition(int[] array, int start, int stop, int pivotIx) {
        int pivot = array[pivotIx];
        swap(array, pivotIx, stop);
        int middleBarrier = start;
        for (int endBarrier = start; endBarrier < stop; endBarrier++) {
            if (array[endBarrier] < pivot) {
                // TODO: Move this value into the smaller-than-pivot portion.
                unfinished("GAP_PARTITION");
                middleBarrier++;
            }
        }
        swap(array, middleBarrier, stop);
        return middleBarrier;
    }

    public static void mergeSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);
        merge(array, start, mid, end);
        // Part 1 records both conceptual splits and completed merges; see the tracing reference.
    }

    private static void merge(int[] array, int start, int mid, int end) {
        int[] temporary = new int[end - start + 1];
        int left = start;
        int right = mid + 1;
        int out = 0;
        while (left <= mid && right <= end) {
            if (array[left] <= array[right]) {
                // TODO: Copy the next left value and advance both indices.
                unfinished("GAP_MERGE_LEFT");
            } else {
                temporary[out++] = array[right++];
            }
        }
        while (left <= mid) {
            temporary[out++] = array[left++];
        }
        while (right <= end) {
            temporary[out++] = array[right++];
        }
        for (int i = 0; i < temporary.length; i++) {
            // TODO: Copy the merged value back into the original range.
            unfinished("GAP_MERGE_COPY");
        }
    }

    private static void unfinished(String task) {
        throw new UnsupportedOperationException("Complete the TODO: " + task);
    }

    private static void swap(int[] array, int first, int second) {
        int value = array[first];
        array[first] = array[second];
        array[second] = value;
    }
}
