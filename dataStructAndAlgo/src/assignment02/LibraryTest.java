package assignment02;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @Test
    public void testEmpty() {
        Library lib = new Library();
        assertNull(lib.lookup(978037429279L));

        ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 0);

        assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
        assertFalse(lib.checkin(978037429279L));
        assertFalse(lib.checkin("Jane Doe"));
    }

    @Test
    public void testNonEmpty() {

        var lib = new Library();
        // test a small library
        lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
        lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
        lib.add(9780446580342L, "David Baldacci", "Simple Genius");

        assertNull(lib.lookup(9780330351690L)); //not checked out
        var res = lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
        assertTrue(res);
        var booksCheckedOut = lib.lookup("Jane Doe");
        assertEquals(booksCheckedOut.size(), 1);
        assertEquals(booksCheckedOut.get(0),new Book(9780330351690L, "Jon Krakauer", "Into the Wild"));
        assertEquals(booksCheckedOut.get(0).getHolder(), "Jane Doe");
        assertEquals(booksCheckedOut.get(0).getDueDate(),new GregorianCalendar(2008, 1, 1));
        res = lib.checkin(9780330351690L);
        assertTrue(res);
        res = lib.checkin("Jane Doe");
        assertFalse(res);
    }

    @Test
    public void testLargeLibrary(){
        var lib = new Library();
        lib.addAll("/Users/olivia/cs6012/dataStructAndAlgo/src/assignment02/Mushroom_Publishing.txt");
        //Jane checked out 2 books
        var res1= lib.checkout(9781843190363L,"Jane",11,12,2022);
        var res2= lib.checkout(9781843190394L,"Jane",11,12,2022);
        //because the 2 books can be checked out
        assertTrue(res1);
        assertTrue(res2);
        //use ISBN to look for its holder
        assertEquals(lib.lookup(9781843190363L),"Jane");
        //use holder to look for all the books he checked out
        assertEquals(lib.lookup("Jane").get(1).getHolder(),"Jane");
        //use holder to check in
        var res3 = lib.checkin("Jane");
        //because jane has books to check in
        assertTrue(res3);
        //2 books should be checked in and their holder is set to null
        assertNull(lib.lookup(9781843190363L));
        assertNull(lib.lookup(9781843190394L));
    }

}

