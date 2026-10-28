import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Random;

/** Provided timing harness. Generation, copying, checking and output are untimed.
 * CLI: --sort all|insertion|selection|quick|merge --sizes 250,500,1000
 *      --trials 5 --warmups 3 --seed 305 --output sort_runtimes.csv
 * Existing CSVs are never overwritten: choose a different output path per run.
 */
public class RuntimeAnalysis {
    private static final String[] SORTS = {"insertion", "selection", "quick", "merge"};
    private static final int[] SMALL = {250, 500, 1000, 2000, 4000};
    private static final int[] LARGE = {1000, 2000, 4000, 8000, 16000};

    public static void main(String[] args) throws IOException {
        String chosenSort = "all";
        String output = "sort_runtimes.csv";
        String order = "random";
        String pivot = "first";
        String seriesLabel = null;
        int[] requestedSizes = null;
        int trials = 5;
        int warmups = 3;
        long seed = 305;
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
            for (int size : requestedSizes) if (size <= 0) throw new IllegalArgumentException("Sizes must be positive");
        }

        if (seriesLabel != null && !seriesLabel.matches("[A-Za-z0-9 _.-]+"))
            throw new IllegalArgumentException("Series label: use letters, digits, spaces, underscores, dots or hyphens");
        if (!order.matches("[A-Za-z0-9_-]+") || !pivot.matches("[A-Za-z0-9_-]+"))
            throw new IllegalArgumentException("Input/pivot labels must be simple words without commas");
        Path path = Path.of(output);
        // CREATE_NEW protects earlier measurements, including under concurrent runs.
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
                java.nio.file.StandardOpenOption.CREATE_NEW, java.nio.file.StandardOpenOption.WRITE)) {
            writer.write("sort_type,input_order,pivot,data_size,trial,run_time_ns,seed,warmups,series_label\n");
            for (String sort : SORTS) {
                if (!chosenSort.equals("all") && !chosenSort.equals(sort)) continue;
                int[] sizes = requestedSizes != null ? requestedSizes :
                        (sort.equals("insertion") || sort.equals("selection") ? SMALL : LARGE);
                for (int size : sizes) {
                    for (int trial = -warmups; trial < trials; trial++) {
                        // Identical seed/size/trial gives identical inputs across algorithms.
                        Random random = new Random(seed + 1_000_003L * size + trial);
                        int[] input = generateArray(size, random);
                        int[] expected = input.clone();
                        Arrays.sort(expected);
                        int[] working = input.clone();
                        long begin = System.nanoTime();
                        sort(sort, working, pivot);
                        long elapsed = System.nanoTime() - begin;
                        if (!Arrays.equals(expected, working)) {
                            throw new IllegalStateException(sort + " produced incorrect output at size " + size);
                        }
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

    /** Modify this generator for the input-order experiments; preserve exact size. */
    public static int[] generateArray(int size, Random random) {
        int[] input = new int[size];
        for (int i = 0; i < size; i++) input[i] = random.nextInt(1001);
        return input;
    }

    private static void sort(String sort, int[] array, String pivot) {
        switch (sort) {
            case "insertion": Sorts.insertionSort(array, 0, array.length - 1); break;
            case "selection": Sorts.selectionSort(array, 0, array.length - 1); break;
            case "quick": Sorts.quickSort(array, 0, array.length - 1); break;
            case "merge": Sorts.mergeSort(array, 0, array.length - 1); break;
            default: throw new IllegalArgumentException(sort);
        }
    }

}
