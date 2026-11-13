import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class DiscussionProblems {

    // Question 1
    public static int[] sortAscending(int[] nums) {
        // TODO
        return new int[0];
    }

    // Question 2
    public static int[] orderPackets(int[] timestamps, int k) {
        // TODO
        return new int[0];
    }

    // Question 3
    public static int lastRobotStanding(int[] power) {
        // TODO
        return 0;
    }

    // Question 4
    public static int minStudyRooms(int[][] bookings) {
        ArrayList<int[]> sorted = new ArrayList<>();
        for (int[] booking : bookings) {
            sorted.add(booking);
        }
        Collections.sort(sorted, (a, b) -> a[0] - b[0]);

        // TODO
        return 0;
    }

    // Question 5
    public static int examCutoff(int[] scores, int k) {
        // TODO
        return 0;
    }

    // Question 6
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        // TODO
        return 0;
    }

    // Question 7
    public static int kthHottestReading(int[] readings, int k) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {

    }
}
