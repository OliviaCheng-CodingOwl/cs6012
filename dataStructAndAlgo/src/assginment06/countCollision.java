package assginment06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class countCollision {
    static List<String> wordsToAdd = readFromFile(new File("dictionary.txt"));

    public static void main(String[] args) {
        int capacity = 2;
        while (capacity < Math.pow(2, 16)) {
            ChainingHashTable good = new ChainingHashTable(capacity, new GoodHashFunctor());
            good.addAll(wordsToAdd);

//            ChainingHashTable mediocre = new ChainingHashTable(capacity, new MediocreHashFunctor());
//            mediocre.addAll(wordsToAdd);

//            ChainingHashTable bad = new ChainingHashTable(capacity, new BadHashFunctor());
//            bad.addAll(wordsToAdd);

//            ChainingHashTable Java = new ChainingHashTable(capacity, new JavaHashFunctor());
//            Java.addAll(wordsToAdd);
//            System.out.print(String.format("Java, %d, %d\n", capacity, Java.collisionNum()));

            System.out.print(String.format("good, %d, %d\n", capacity, good.collisionNum()));

            capacity = capacity * 2;
        }
    }


    public static List<String> readFromFile(File file) {
        ArrayList<String> words = new ArrayList<String>();

        try (Scanner fileInput = new Scanner(file)) {
            fileInput.useDelimiter("\\s*[^a-zA-Z]\\s*");

            while (fileInput.hasNext()) {
                String s = fileInput.next();
                if (!s.equals("")) {
                    words.add(s.toLowerCase());
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("File " + file + " cannot be found.");
        }

        System.out.println("Document is " + words);

        return words;
    }
}

