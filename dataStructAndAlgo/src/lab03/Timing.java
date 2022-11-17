package lab03;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class Timing {
    static int LOOP = 1000;

    public static SortedSet<Integer> createSortedSet(int size) {
        SortedSet<Integer> set = new ConcurrentSkipListSet<>();
        for (int i = 0; i < size; ++i) {
            set.add(i);
        }
        return set;
    }

    public static double elapsedTime(SortedSet<Integer> set) {
        Random random = new Random();
        double totalTime = 0;
        for (int i = 0; i < LOOP; ++i) {
            int key = random.nextInt(set.size() - 1);
            long start = System.nanoTime();
            set.contains(key);
            long end = System.nanoTime();
            totalTime += end - start;
        }
        return totalTime / (double) LOOP;
    }

    public static void main(String[] args) throws IOException {
//        First run give outline
        SortedSet<Integer> set = createSortedSet(1 << 20);
        double elapsedTime = elapsedTime(set);
        FileWriter writer = new FileWriter("data.csv", false);
        writer.write("Timing of contains() on SortedSet,size (N),time (ns)");
        for (int i = 10; i <= 20; ++i) {
            int size = 1 << i;
            set = createSortedSet(size);
            elapsedTime = elapsedTime(set);
            System.out.println(size + ": " + elapsedTime);
            writer.write(String.format("trial, %d, %f\n", size, elapsedTime));
        }
        writer.close();
    }
}
