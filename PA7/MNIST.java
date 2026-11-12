/*
 * Name: TODO
 * PID: TODO
 *
 * Complete getClosestMatches(). The other methods are provided.
 */
import java.io.*;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

// TODO: Add class Javadoc.
public class MNIST {

    public static final int NUM_TEST = 100; // quick check; optionally use 1_000
    public static final int NUM_TRAIN = 60_000; // can be up to 60k

    public static final int K = 3;
    public static final int NUM_CLASSES = 10;
    public static final int IMG_DIM = 28;

    private static final float[][] TRAIN_IMAGES = new float[NUM_TRAIN][IMG_DIM * IMG_DIM];
    private static final short[] TRAIN_LABELS = new short[NUM_TRAIN];
    public static final float[][] TEST_IMAGES = new float[NUM_TEST][IMG_DIM * IMG_DIM];
    private static final short[] TEST_LABELS = new short[NUM_TEST];

    /**
     * Custom class for storing images, labels, and priority values together
     */
    public static class DataHolder {
        int label;
        float priority;
        float[] image;

        /**
         * Constructor that creates a new DataHolder
         *
         * @param label A number from 0 to 9 that describes the image.
         * @param priority A value that determines how close this image is
         *                 to the current image being tested
         *                 lower priority = better fit
         * @param image A flattened array representing the image
         */
        public DataHolder(int label, float priority, float[] image) {
            this.label = label;
            this.priority = priority;
            this.image = image;
        }

    }

    /**
     * Provided code.
     * Calculate Euclidean distance between two vectors (1-D arrays)
     * @param img1 the first array
     * @param img2 the second array
     * @return the Euclidean distance between img1 and img2
     * @throws IllegalArgumentException if the arrays have different lengths
     */
    public static float totalDist(float[] img1, float[] img2) throws IllegalArgumentException {
        if (img1.length != img2.length) {
            throw new IllegalArgumentException();
        }

        float running = 0;
        for (int i = 0; i < img1.length; i++) {
            running += (img1[i] - img2[i]) * (img1[i] - img2[i]);
        }
        return (float) Math.pow(running, 0.5);
    }

    // TODO: Add Javadoc.
    public static DataHolder[] getClosestMatches(float[] image, int k) {
        // TODO: Implement this method using your own MyPriorityQueue.
        return null;
    }

    /**
     * Computes the distance from the input to every training image.
     * Provided code. Position i corresponds to training image i.
     *
     * @param image the flattened input image
     * @return one distance for each training image
     * @throws IllegalArgumentException if the input has the wrong length
     */
    public static float[] calculateDistances(float[] image) throws IllegalArgumentException {
        float[] distances = new float[TRAIN_IMAGES.length];
        for (int index = 0; index < TRAIN_IMAGES.length; index++) {
            distances[index] = totalDist(image, TRAIN_IMAGES[index]);
        }
        return distances;
    }

    /**
     * Looks up the training images corresponding to selected distances.
     * Provided code. Equal distances are matched to distinct training images.
     *
     * @param allDistances the array returned by calculateDistances
     * @param closestDistances the selected distances in closest-first order
     * @return the corresponding images, labels, and distances in the same order
     * @throws IllegalArgumentException if the arrays cannot identify distinct matches
     */
    public static DataHolder[] toDataHolders(float[] allDistances,
            float[] closestDistances) throws IllegalArgumentException {
        if (allDistances.length != TRAIN_IMAGES.length
                || closestDistances.length > allDistances.length) {
            throw new IllegalArgumentException("Invalid distance array lengths.");
        }

        DataHolder[] matches = new DataHolder[closestDistances.length];
        boolean[] selected = new boolean[allDistances.length];
        for (int match = 0; match < closestDistances.length; match++) {
            int trainingIndex = -1;
            for (int index = 0; index < allDistances.length; index++) {
                if (!selected[index]
                        && Float.compare(allDistances[index], closestDistances[match]) == 0) {
                    trainingIndex = index;
                    break;
                }
            }
            if (trainingIndex == -1) {
                throw new IllegalArgumentException("Selected distance has no unused match.");
            }
            selected[trainingIndex] = true;
            matches[match] = new DataHolder(TRAIN_LABELS[trainingIndex],
                    allDistances[trainingIndex], TRAIN_IMAGES[trainingIndex]);
        }
        return matches;
    }

    /**
     * Provided code.
     * Makes a prediction using the K-NN Classifier
     * closestMatches contains at least one neighbor.
     *
     * @param closestMatches the array of DataHolders containing the k closest matches
     */
    public static int predict(DataHolder[] closestMatches) {

        //populate counts array to find most "popular" class
        int[] counts = new int[NUM_CLASSES];
        for (DataHolder closestMatch : closestMatches) {
            counts[closestMatch.label]++;
        }

        int guess = -1;
        int highestCount = 0;

        for (int i = 0; i < counts.length; i++) {
            //there is bias towards lower labels
            if (counts[i] > highestCount) {
                highestCount = counts[i];
                guess = i;
            }
        }

        return guess;
    }

    /**
     * Loads MNIST data into the specified arrays
     *
     * @param imgFile the link to MNIST images
     * @param lblFile the link to MNIST labels
     * @param imgArr the array to store images (dimension nx784)
     * @param lblArr the array to store labels (dimension n)
     */
    public static void loadData(String imgFile, String lblFile, float[][] imgArr, short[] lblArr) throws IOException {

        InputStream stream;
        File data = Paths.get("src", "data", imgFile).toFile();
        try {
            stream = new FileInputStream(data);
        } catch (FileNotFoundException f) {
            data = Paths.get("data", imgFile).toFile();
            stream = new FileInputStream(data);
        }
        InputStream imgIn = new GZIPInputStream(stream);

        data = Paths.get("src", "data", lblFile).toFile();
        try {
            stream = new FileInputStream(data);
        } catch (FileNotFoundException f) {
            data = Paths.get("data", lblFile).toFile();
            stream = new FileInputStream(data);
        }
        InputStream lblIn = new GZIPInputStream(stream);

        // first 16 bytes of image data are metadata
        // first 8 bytes of label data are metadata
        byte[] tempBuffer = new byte[16];
        imgIn.read(tempBuffer, 0, 16);
        lblIn.read(tempBuffer, 0, 8);

        byte[] dataBuffer = new byte[1];

        for (int i = 0; i < lblArr.length; i++){
            lblIn.read(dataBuffer, 0, 1);
            lblArr[i] = (short) (dataBuffer[0] & 0xFF);

            for (int j = 0; j < IMG_DIM * IMG_DIM; j++){
                imgIn.read(dataBuffer, 0, 1);
                float pixelVal = (dataBuffer[0] & 0xFF) / 255.f;
                imgArr[i][j] = pixelVal;
            }
        }
    }

    /**
     * Calls loadData on each MNIST dataset
     */
    public static void downloadMNIST() {
        long startTime = System.currentTimeMillis();

        String trainImagesFile = "train-images-idx3-ubyte.gz";
        String trainLabelsFile = "train-labels-idx1-ubyte.gz";
        String testImagesFile = "t10k-images-idx3-ubyte.gz";
        String testLabelsFile = "t10k-labels-idx1-ubyte.gz";

        // load all training and test data
        try {
            loadData(trainImagesFile, trainLabelsFile, TRAIN_IMAGES, TRAIN_LABELS);
            loadData(testImagesFile, testLabelsFile, TEST_IMAGES, TEST_LABELS);
            System.out.println("MNIST data loaded!");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("MNIST data loading failed!");
            System.exit(1);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime)/1000. + " seconds");
    }

    public static void main(String[] args) {
        downloadMNIST();

        long startTime = System.currentTimeMillis();
        int numWrong = 0;
        for (int i = 0; i < NUM_TEST; i++) {
            DataHolder[] closestNeighbors = getClosestMatches(TEST_IMAGES[i], K);
            int guess = predict(closestNeighbors);
            if (guess != TEST_LABELS[i]) {
                numWrong++;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Accuracy: " + (1 - numWrong / (float) NUM_TEST));
        System.out.println("Spent " + (endTime - startTime)/1000. + " seconds testing "
            + NUM_TEST + " images with k = " + K);
    }
}
