package org.library.Model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class CatalogTest {

    final String EXPECTED_BOOK_ID = "9921";
    final String EXPECTED_BOOK_TITLE = "TESTING BOOK";
    final String EXPECTED_AUTHOR = "IRODOV";
    final int EXPECTED_COPIES = 12;
    final int INITIAL_CATALOG_BOOK_COUNT=Catalog.getBookCount();
    LibraryBook libraryBook;
    PricingStrategy pricingStrategy;
    final int EXPECTED_SINGLE_PER_DAY_COST = 1;
    final int EXPECTED_DOUBLE_PER_DAY_COST = 2;

    @Before
    public void setUp() {
        HashMap<String,String> hm= new HashMap<>();
        hm.put("book_id",EXPECTED_BOOK_ID);
        hm.put("author",EXPECTED_AUTHOR);
        hm.put("copies",String.valueOf(EXPECTED_COPIES));
        hm.put("book_title",EXPECTED_BOOK_TITLE);
        libraryBook=new LibraryBook(hm);
        pricingStrategy=Catalog.getPricingStrategy();
    }

    @After
    public void tearDown() {
        Database.deleteBook(EXPECTED_BOOK_ID);
    }

    @Test
    public void getBookTest(){
        LibraryBook book=Catalog.getBook(Integer.parseInt(EXPECTED_BOOK_ID));
        Assert.assertNotNull(book);
        Assert.assertEquals(EXPECTED_BOOK_ID,book.getBookId());
        Assert.assertEquals(EXPECTED_BOOK_TITLE,book.getBookTitle());
        Assert.assertEquals(EXPECTED_AUTHOR,book.getAuthor());
        Assert.assertEquals(EXPECTED_COPIES,book.getCopies());
    }

    @Test
    public void getBookCountTest(){
        Assert.assertEquals(Catalog.getBookCount(), INITIAL_CATALOG_BOOK_COUNT+1);
    }

    @Test
    public void updatePricingStrategyAndGetPricingStrategyTest(){
        int initialStrategy= pricingStrategy.getPerDayCost();
        Catalog.updatePricingStrategy(1);
        Assert.assertEquals(Catalog.getPricingStrategy().getPerDayCost(),EXPECTED_SINGLE_PER_DAY_COST);
        Catalog.updatePricingStrategy(2);
        Assert.assertEquals(Catalog.getPricingStrategy().getPerDayCost(),EXPECTED_DOUBLE_PER_DAY_COST);
        Catalog.updatePricingStrategy(initialStrategy);
    }
}
