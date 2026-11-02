import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Random;

/** Timing harness. Generation, copying, correctness checking and output are untimed. */
public class RuntimeAnalysis {
    // STUDENT SETTINGS: choose an experiment, then use your IDE's Run button.
    // task1, task2, task3, task4, task5_ascending, task5_descending,
    // task6_ascending, task6_descending
    private static final String EXPERIMENT = "task1";
    // null uses the defaults below. Example override: {250, 500, 1000, 2000}
    // Use matching sizes for every series in a comparison.
    private static final int[] INPUT_SIZES = null;
    private static final int TRIALS = 5;
    private static final int WARMUPS = 3;
    private static final long SEED = 305;

    private static final String[] SORTS = {"insertion", "selection", "quick", "merge"};
    private static final int[] SMALL = {250, 500, 1000, 2000, 4000};
    private static final int[] LARGE = {1000, 2000, 4000, 8000, 16000};

    // Presets select the sort, labels and filename ONLY.
    // You must implement each task's generator and any pivot change yourself.
    private static String[] experimentSettings(String experiment) {
        switch (experiment) {
            case "task1": return new String[]{"all", "random", "first"};
            case "task2": return new String[]{"insertion", "favorable", "first"};
            case "task3": return new String[]{"quick", "unfavorable", "first"};
            case "task4": return new String[]{"quick", "unfavorable", "middle"};
            case "task5_ascending": return new String[]{"selection", "ascending", "first"};
            case "task5_descending": return new String[]{"selection", "descending", "first"};
            case "task6_ascending": return new String[]{"merge", "ascending", "first"};
            case "task6_descending": return new String[]{"merge", "descending", "first"};
            default: throw new IllegalArgumentException("Unknown EXPERIMENT: " + experiment);
        }
    }

    public static void main(String[] args) throws IOException {
        String[] settings = experimentSettings(EXPERIMENT);
        String chosenSort = settings[0];
        String order = settings[1];
        String pivot = settings[2];
        String output = EXPERIMENT + ".csv";
        String seriesLabel = null;
        int[] requestedSizes = INPUT_SIZES;
        int trials = TRIALS;
        int warmups = WARMUPS;
        long seed = SEED;
        // Optional command-line overrides remain available.
        for (int i = 0; i < args.length; i += 2) {
            if (i + 1 >= args.length) throw new IllegalArgumentException("Missing value: " + args[i]);
            String value = args[i + 1];
            switch (args[i]) {
                case "--sort": chosenSort = value; break;
                case "--output": output = value; break;
                case "--series-label": seriesLabel = value; break;
                case "--input-label": order = value; break;
                case "--pivot-label": pivot = value; break;
                case "--trials": trials = Integer.parseInt(value); break;
                case "--warmups": warmups = Integer.parseInt(value); break;
                case "--seed": seed = Long.parseLong(value); break;
                case "--sizes":
                    String[] pieces = value.split(",");
                    requestedSizes = new int[pieces.length];
                    for (int j = 0; j < pieces.length; j++) requestedSizes[j] = Integer.parseInt(pieces[j]);
                    break;
                default: throw new IllegalArgumentException("Unknown option: " + args[i]);
            }
        }
        if (trials <= 0 || warmups < 0) throw new IllegalArgumentException("Require trials > 0, warmups >= 0");
        boolean validSort = chosenSort.equals("all");
        for (String sort : SORTS) validSort |= chosenSort.equals(sort);
        if (!validSort) throw new IllegalArgumentException("Unknown sort: " + chosenSort);
        if (requestedSizes != null) {
            if (requestedSizes.length == 0) throw new IllegalArgumentException("Provide at least one input size");
            for (int size : requestedSizes) if (size <= 0) throw new IllegalArgumentException("Sizes must be positive");
        }
        if (seriesLabel != null && !seriesLabel.matches("[A-Za-z0-9 _.-]+"))
            throw new IllegalArgumentException("Series label: use letters, digits, spaces, underscores, dots or hyphens");
        if (!order.matches("[A-Za-z0-9_-]+") || !pivot.matches("[A-Za-z0-9_-]+"))
            throw new IllegalArgumentException("Input/pivot labels must be simple words without commas");
        Path path = Path.of(output);
        if (Files.exists(path)) {
            throw new IOException("Results already exist: " + path.toAbsolutePath()
                    + ". Rename that CSV before rerunning this experiment. Earlier results are preserved.");
        }
        System.out.println("Experiment: " + EXPERIMENT + "; input label: " + order + "; pivot label: " + pivot);
        System.out.println("Labels do not change your generator or pivot implementation.");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {
            writer.write("sort_type,input_order,pivot,data_size,trial,run_time_ns,seed,warmups,series_label\n");
            for (String sort : SORTS) {
                if (!chosenSort.equals("all") && !chosenSort.equals(sort)) continue;
                int[] sizes = requestedSizes != null ? requestedSizes :
                        (sort.equals("insertion") || sort.equals("selection") ? SMALL : LARGE);
                for (int size : sizes) {
                    for (int trial = -warmups; trial < trials; trial++) {
                        Random random = new Random(seed + 1_000_003L * size + trial);
                        int[] input = generateArray(size, random);
                        if (input == null || input.length != size)
                            throw new IllegalStateException("generateArray must return exactly " + size + " elements");
                        int[] expected = input.clone();
                        Arrays.sort(expected);
                        int[] working = input.clone();
                        long begin = System.nanoTime();
                        sort(sort, working);
                        long elapsed = System.nanoTime() - begin;
                        if (!Arrays.equals(expected, working))
                            throw new IllegalStateException(sort + " produced incorrect output at size " + size);
                        if (trial >= 0) {
                            writer.write(sort + "," + order + "," + (sort.equals("quick") ? pivot : "n/a") + "," +
                                    size + "," + trial + "," + elapsed + "," + seed + "," + warmups + "," +
                                    (seriesLabel == null ? "" : seriesLabel) + "\n");
                        }
                    }
                    writer.flush();
                    System.out.println(sort + ": " + size + " elements, " + trials + " measured trials");
                }
            }
        }
        System.out.println("Wrote " + path.toAbsolutePath());
    }

    /** Modify for the input-order experiments; preserve the requested size. */
    public static int[] generateArray(int size, Random random) {
        int[] input = new int[size];
        for (int i = 0; i < size; i++) input[i] = random.nextInt(1001);
        return input;
    }

    private static void sort(String sort, int[] array) {
        switch (sort) {
            case "insertion": Sorts.insertionSort(array, 0, array.length - 1); break;
            case "selection": Sorts.selectionSort(array, 0, array.length - 1); break;
            case "quick": Sorts.quickSort(array, 0, array.length - 1); break;
            case "merge": Sorts.mergeSort(array, 0, array.length - 1); break;
            default: throw new IllegalArgumentException(sort);
        }
    }
}
