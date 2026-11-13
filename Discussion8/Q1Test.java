import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Q1Test {

    @Test
    public void testExample() {
        assertArrayEquals(new int[] {1, 2, 4, 5},
                DiscussionProblems.sortAscending(new int[] {5, 1, 4, 2}));
    }

    @Test
    public void testEmpty() {
        assertArrayEquals(new int[] {}, DiscussionProblems.sortAscending(new int[] {}));
    }

    @Test
    public void testOneElement() {
        assertArrayEquals(new int[] {7}, DiscussionProblems.sortAscending(new int[] {7}));
    }

    @Test
    public void testAlreadySorted() {
        assertArrayEquals(new int[] {1, 2, 3, 4},
                DiscussionProblems.sortAscending(new int[] {1, 2, 3, 4}));
    }

    @Test
    public void testReverseSorted() {
        assertArrayEquals(new int[] {1, 2, 3, 4},
                DiscussionProblems.sortAscending(new int[] {4, 3, 2, 1}));
    }

    @Test
    public void testDuplicates() {
        assertArrayEquals(new int[] {2, 2, 3, 3, 3, 9},
                DiscussionProblems.sortAscending(new int[] {3, 9, 2, 3, 2, 3}));
    }

    @Test
    public void testNegatives() {
        assertArrayEquals(new int[] {-8, -3, 0, 4, 10},
                DiscussionProblems.sortAscending(new int[] {0, -3, 10, -8, 4}));
    }
}
