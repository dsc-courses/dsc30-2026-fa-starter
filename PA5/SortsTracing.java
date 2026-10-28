/** Hand-entered whole-array snapshots; no sorting code belongs here.
 * No initial snapshot and no extra final snapshot.
 * Insertion/selection: after each outer pass (n-1 snapshots).
 * Quick: after each completed partition; recurse left before right.
 * Merge: after each completed merge; recurse left before right.
 * Preserve repeated states. See TRACING_REFERENCE.md for the exact procedures.
 * int[][] avoids Java generics: each row is one int[] snapshot.
 */
public class SortsTracing {
    // Input: [7, 0, 9, 4, 1, 3, 6]
    public int[][] InsertionSortRandomList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [0, 1, 3, 4, 6, 7, 9]
    public int[][] InsertionSortSortedList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [9, 7, 6, 4, 3, 1, 0]
    public int[][] InsertionSortReversedList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [7, 0, 9, 4, 1, 3, 6]
    public int[][] SelectionSortRandomList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [0, 1, 3, 4, 6, 7, 9]
    public int[][] SelectionSortSortedList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [9, 7, 6, 4, 3, 1, 0]
    public int[][] SelectionSortReversedList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [6, 1, 9, 3, 2]
    public int[][] QuickSortRandomList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [1, 2, 3, 6, 9]
    public int[][] QuickSortSortedList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [9, 6, 3, 2, 1]
    public int[][] QuickSortReversedList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [6, 1, 9, 3, 2]
    public int[][] MergeSortRandomList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [1, 2, 3, 6, 9]
    public int[][] MergeSortSortedList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

    // Input: [9, 6, 3, 2, 1]
    public int[][] MergeSortReversedList() {
        // TODO: Enter each required snapshot by hand.
        return new int[][] {};
    }

}
