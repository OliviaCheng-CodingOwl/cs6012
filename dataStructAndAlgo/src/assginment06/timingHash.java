package assginment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static assginment06.countCollision.readFromFile;
import static assignment04.SortUtil.*;
import static assignment04.SortUtil.quicksort;

public class timingHash {
    static List<String> wordsToAdd = readFromFile(new File("dictionary.txt"));
    private static final int ITER_COUNT = 1;


    public static void main(String[] args) {
        int capacity=2;
        while (capacity < Math.pow(2, 16)){

            ChainingHashTable good = new ChainingHashTable(capacity, new MediocreHashFunctor());
            long totalTime = 0;

            for (int iter = 0; iter < ITER_COUNT; iter++) {
                System.nanoTime();
                // TIME IT!
                long start = System.nanoTime();
                good.contains("deaf");
                long stop = System.nanoTime();
                totalTime += stop - start;
            }
            double averageTime = totalTime / (double) ITER_COUNT;
//            System.out.print(String.format("good_addAll, %d, %f\n", capacity, averageTime));
            System.out.println(averageTime);
            capacity=capacity*2;

        }
    }

}
