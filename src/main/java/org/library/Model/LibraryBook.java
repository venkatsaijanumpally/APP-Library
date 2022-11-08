package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.Map;

public class LibraryBook implements LibraryItem,Book {

    @BsonProperty(value = "book_title")
    String bookTitle;

    @BsonProperty(value = "book_id")
    String bookId;

    @BsonProperty(value = "author")
    String author;

    @BsonProperty(value = "copies")
    private int copies;

    public LibraryBook(){ }

    public LibraryBook(Map<String,String> attributes){
        bookTitle=attributes.get("book_title");
        bookId=attributes.get("book_id");
        author=attributes.get("author");
        copies=Integer.parseInt(attributes.get("copies"));
        Database.addBook(this);
    }

    public static Iterable<LibraryBook> getBooks() {
        return Database.getBooks();
    }

    @Override
    public void addItemCopies(int copies) {
        this.copies+=copies;
    }

    @Override
    public boolean available() {
        return copies>0;
    }

    public void decrementCopies(){
        Database.updateCopiesOfBook(bookId,copies-1);
    }

    public void incrementCopies(){
        Database.updateCopiesOfBook(bookId,copies+1);
    }

    public int getCopies() {
        return copies;
    }

    public String getAuthor() {
        return author;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookId() {
        return bookId;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
