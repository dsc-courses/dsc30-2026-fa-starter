/*
 * Name: TODO
 * PID: TODO
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * TODO: Add your class description here
 *
 * @author TODO
 * @since TODO
 */
public class StudentAttendance {

    public static final int NUM_COLLEGES = 8;
    public static final String YEAR_FORMAT = "%02d";
    public static final int MIN_REPORT_YEAR = 21;
    public static final int MAX_REPORT_YEAR = 25;

    /**
     * College names indexed by college digit.
     */
    public static final String[] COLLEGE_NAMES = {
        "", "Revelle", "Muir", "Marshall", "Warren",
        "Roosevelt", "Sixth", "Seventh", "Eighth"
    };

    private MyHashMap studentCounts;
    private MyHashMap yearCounts;
    private MyHashMap collegeCounts;
    private int totalLectures;
    private String dataFolder;

    public StudentAttendance() {
        // TODO
    }

    public StudentAttendance(String dataFolder) {
        // TODO
    }

    /**
     * Read every line of an attendance file.
     *
     * @param filepath Path of the attendance file
     * @return The student IDs in file order
     * @throws FileNotFoundException If the file does not exist
     */
    public static LinkedList<String> readAttendanceFile(String filepath)
            throws FileNotFoundException {
        LinkedList<String> ids = new LinkedList<>();
        Scanner scanner = new Scanner(new File(filepath));
        while (scanner.hasNextLine()) {
            ids.add(scanner.nextLine());
        }
        scanner.close();
        return ids;
    }

    public void updateAttendance(String date) throws FileNotFoundException {
        // TODO
    }

    public int getAttendance(String id) {
        // TODO
        return 0;
    }

    public double calculateGrade(String id) {
        // TODO
        return 0.0;
    }

    public boolean dropStudent(String id) {
        // TODO
        return false;
    }

    public int totalUniqueStudents() {
        // TODO
        return 0;
    }

    public int getTotalLectures() {
        // TODO
        return 0;
    }

    public int yearAttendance(int year) {
        // TODO
        return 0;
    }

    public int collegeAttendance(int college) {
        // TODO
        return 0;
    }

    /**
     * Example usage.
     */
    public static void main(String[] args) {
        // Lecture dates to record. Each date reads data/attendance_<date>.txt,
        // so add more dates here to test with more lectures.
        String[] dates = { "09_25", "09_30" };
        StudentAttendance attendance = new StudentAttendance();
        // Record each lecture. updateAttendance throws if the file is missing.
        for (String date : dates) {
            try {
                attendance.updateAttendance(date);
            } catch (FileNotFoundException e) {
                System.err.println("No attendance file for " + date);
            }
        }

        // Overall totals across every recorded lecture
        System.out.println("Total lectures: " + attendance.getTotalLectures());
        System.out.println("Unique students: "
                + attendance.totalUniqueStudents());
        // Check-ins for each entry year, printed as 2021 through 2025
        System.out.println("Attendance by entry year:");
        for (int year = MIN_REPORT_YEAR; year <= MAX_REPORT_YEAR; year++) {
            System.out.printf("  20" + YEAR_FORMAT + ": %d%n", year,
                    attendance.yearAttendance(year));
        }
        // Check-ins for each college digit, with its name from COLLEGE_NAMES
        System.out.println("Attendance by college:");
        for (int college = 1; college <= NUM_COLLEGES; college++) {
            System.out.printf("  %d %s: %d%n", college,
                    COLLEGE_NAMES[college],
                    attendance.collegeAttendance(college));
        }
    }
}
