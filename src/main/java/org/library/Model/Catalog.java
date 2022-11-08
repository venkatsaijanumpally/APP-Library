package org.library.Model;

import java.util.HashMap;

public class Catalog {

    private static int BookCount;
    public static HashMap<Integer,LibraryBook> Books = new HashMap<>();
    private static PricingStrategy pricingStrategy;

    static {
        addBooks();
        BookCount= Books.size();
        PricingStrategy currentStrategy=Database.getPricingStrategy();
        if(currentStrategy!=null)
            pricingStrategy=currentStrategy;
        else {
            pricingStrategy=new SinglePricingStrategy();
            Database.updatePricingStrategy(pricingStrategy);
        }
    }

    private static void addBooks() {
        for(LibraryBook book: LibraryBook.getBooks()){
            Books.put(Integer.parseInt(book.getBookId()),book);
        }
    }

    public static LibraryBook getBook(int book_id){
        return Books.get(book_id);
    }

    public static void addBook(int book_id, LibraryBook book){
        Books.put(book_id,book);
        BookCount++;
    }

    public static void removeBook(int book_id){
        Books.remove(book_id);
        BookCount--;
    }

    public static HashMap<Integer,LibraryBook> getBooks(){
        return Books;
    }

    public static int getBookCount() {
        return BookCount;
    }

    public static void setBookCount(int bookCount) {
        BookCount = bookCount;
    }

    public static void updatePricingStrategy(int strategy){
        if(strategy == pricingStrategy.getPerDayCost())
            return;
        pricingStrategy=strategy==1?new SinglePricingStrategy():new DoublePricingStrategy();
        Database.updatePricingStrategy(pricingStrategy);
    }

    public static PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }
}
