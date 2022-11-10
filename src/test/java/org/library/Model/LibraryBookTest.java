package org.library.Model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.HashMap;

public class LibraryBookTest {
    final String EXPECTED_BOOK_ID = "9921";
    final String EXPECTED_BOOK_TITLE = "TESTING BOOK";
    final String EXPECTED_AUTHOR = "IRODOV";
    final int EXPECTED_COPIES = 12;
    LibraryBook libraryBook;

    @Before
    public void setUp() {
        HashMap<String,String> hm= new HashMap<>();
        hm.put("book_id",EXPECTED_BOOK_ID);
        hm.put("author",EXPECTED_AUTHOR);
        hm.put("copies",String.valueOf(EXPECTED_COPIES));
        hm.put("book_title",EXPECTED_BOOK_TITLE);
        libraryBook=new LibraryBook(hm);
    }

    @After
    public void tearDown() {
        Database.deleteBook(EXPECTED_BOOK_ID);
    }

    @Test
    public void createLibraryBookTest(){
        Assert.assertEquals(EXPECTED_BOOK_ID,libraryBook.getBookId());
        Assert.assertEquals(EXPECTED_BOOK_TITLE,libraryBook.getBookTitle());
        Assert.assertEquals(EXPECTED_AUTHOR,libraryBook.getAuthor());
        Assert.assertEquals(EXPECTED_COPIES,libraryBook.getCopies());
    }

    @Test
    public void checkIfBookCreated(){
        Assert.assertNotNull(Database.getBook(Integer.parseInt(EXPECTED_BOOK_ID)));
    }

    @Test
    public void decrementCopiesTest(){
        libraryBook.decrementCopies();
        Assert.assertEquals(libraryBook.getCopies(),EXPECTED_COPIES-1);
    }

    @Test
    public void incrementCopiesTest(){
        libraryBook.incrementCopies();
        Assert.assertEquals(libraryBook.getCopies(),EXPECTED_COPIES+1);
    }
}
