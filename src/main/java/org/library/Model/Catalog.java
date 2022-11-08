package org.library.Model;

import java.util.HashMap;

public class Catalog {

    private static int BookCount;
    public static HashMap<Integer,String> BookTitles = new HashMap<>();

    static {

    }

    public static void addBook(int book_id, String title){
        BookTitles.put(book_id,title);
        BookCount++;
    }

    public static void removeBook(int book_id){
        BookTitles.remove(book_id);
        BookCount--;
    }

    public static HashMap<Integer,String> getBookTitles(){
        return BookTitles;
    }

    public static int getBookCount() {
        return BookCount;
    }

    public static void setBookCount(int bookCount) {
        BookCount = bookCount;
    }

    public static void setBookTitles(HashMap<Integer, String> bookTitles) {
        BookTitles = bookTitles;
    }
}
