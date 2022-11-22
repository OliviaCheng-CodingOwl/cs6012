package assignment04;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static assignment04.SortUtil.*;

public class timingSortUtil {
    private static final int ITER_COUNT = 1;

    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter("TimingSortUtil.csv", false)) {
            fw.write("Timing of quickSort,size (N),time (ns)");
            fw.write(System.getProperty("line.separator"));

            Random random = new Random();
            for (int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
                int size = (int) Math.pow(2, exp); // or ..

                ArrayList<Integer> bestCase = generateBestCase(size);
                ArrayList<Integer> averageCase = generateAverageCase(size);
                ArrayList<Integer> worstCase = generateWorstCase(size);


                // Do the experiment multiple times, and average out the results
                long totalTime = 0;
                // SET UP!
                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    System.nanoTime();

                    // TIME IT!
                    long start = System.nanoTime();
                    quicksort(bestCase,Integer::compareTo);
//                    mergesort(worstCase,Integer::compareTo);
                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                double averageTime = totalTime / (double) ITER_COUNT;
                System.out.print(String.format("trial, %d, %f\n", size, averageTime)); // print to console
                fw.write(String.format("trial, %d, %f\n", size, averageTime));
            }
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
