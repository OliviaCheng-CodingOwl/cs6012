package assignment02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LibraryGenericTest {

    @Test
    public void stringLibraryTest() {
        // test a library that uses names (String) to id patrons
        LibraryGeneric<String> lib = new LibraryGeneric<>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        String patron1 = "Jane Doe";

        assertTrue(lib.checkout(9780330351690L, patron1, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron1, 1, 1, 2008));

        var booksCheckedOut1 = lib.lookup(patron1);
        assertEquals(booksCheckedOut1.size(), 2);
        assertTrue(booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut1.get(0).getHolder(), patron1);
        assertEquals(booksCheckedOut1.get(0).getDueDate(), new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut1.get(1).getHolder(), patron1);
        assertEquals(booksCheckedOut1.get(1).getDueDate(), new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron1));

    }

    @Test
    public void phoneNumberTest() {
        // test a library that uses phone numbers (PhoneNumber) to id patrons
        var lib = new LibraryGeneric<PhoneNumber>();
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        PhoneNumber patron2 = new PhoneNumber("801.555.1234");

        assertTrue(lib.checkout(9780330351690L, patron2, 1, 1, 2008));
        assertTrue(lib.checkout(9780374292799L, patron2, 1, 1, 2008));

        ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib.lookup(patron2);

        assertEquals(booksCheckedOut2.size(), 2);
        assertTrue(booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
        assertTrue(booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
        assertEquals(booksCheckedOut2.get(0).getHolder(), patron2);
        assertEquals(booksCheckedOut2.get(0).getDueDate(), new GregorianCalendar(2008, 1, 1));
        assertEquals(booksCheckedOut2.get(1).getHolder(), patron2);
        assertEquals(booksCheckedOut2.get(1).getDueDate(), new GregorianCalendar(2008, 1, 1));

        assertTrue(lib.checkin(patron2));

    }

    @Test
    void getOverdueList() {
        var lib = new LibraryGeneric<String>();
        lib.addAll("/Users/olivia/cs6012/dataStructAndAlgo/src/assignment02/Mushroom_Publishing.txt");
        //Jane checked out 2 books
        var res1 = lib.checkout(9781843190363L, "Jane", 11, 12, 2021);
        var res2 = lib.checkout(9781843190394L, "Jane", 11, 12, 2021);
        ArrayList<LibraryBookGeneric<String>> overDueBooks = new ArrayList<>();
        overDueBooks = lib.getOverdueList(11, 13, 2021);
        assertEquals(overDueBooks.get(0).getIsbn(), 9781843190363L);
        assertEquals(overDueBooks.get(1).getIsbn(), 9781843190394L);
    }

    @Test
    void getInventoryList() {
        var lib = new LibraryGeneric<String>();
        lib.addAll("/Users/olivia/cs6012/dataStructAndAlgo/src/assignment02/Mushroom_Publishing.txt");
        ArrayList<LibraryBookGeneric<String>> libSortedByISBN = lib.getInventoryList();
        assertTrue(isSortedByISBN(libSortedByISBN));
    }
    public boolean isSortedByISBN(ArrayList<LibraryBookGeneric<String>> libSortedByISBN) {
        for (int i = 0; i < libSortedByISBN.size() - 1; i++) {
            if (libSortedByISBN.get(i).getIsbn() > libSortedByISBN.get(i + 1).getIsbn()) {
                return false;
            }
        }
        return true;
    }

    @Test
    void getOrderedByAuthor() {
        var lib = new LibraryGeneric<String>();
        lib.addAll("/Users/olivia/cs6012/dataStructAndAlgo/src/assignment02/Mushroom_Publishing.txt");
        ArrayList<LibraryBookGeneric<String>> libSortedByAuthor = lib.getOrderedByAuthor();
        assertTrue(isSortedByAuthor(libSortedByAuthor));
    }
    public boolean isSortedByAuthor(ArrayList<LibraryBookGeneric<String>> libSortedByAuthor) {
        for (int i = 0; i < libSortedByAuthor.size() - 1; i++) {
            if (libSortedByAuthor.get(i).getAuthor().compareTo(libSortedByAuthor.get(i + 1).getAuthor())>0) {
                return false;
            }
        }
        return true;
    }

}