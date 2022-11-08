package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.library.Impl.ConstantValues;

import java.util.Map;

public class LibraryBook implements LibraryItem,Book {

    @BsonProperty(value = ConstantValues.LIBRARY_BOOK_BOOK_TITLE_LABEL)
    String bookTitle;

    @BsonProperty(value = ConstantValues.LIBRARY_BOOK_BOOK_ID_LABEL)
    String bookId;

    @BsonProperty(value = ConstantValues.LIBRARY_BOOK_BOOK_AUTHOR_LABEL)
    String author;

    @BsonProperty(value = ConstantValues.LIBRARY_BOOK_BOOK_COPIES_LABEL)
    private int copies;

    public LibraryBook(){ }

    public LibraryBook(Map<String,String> attributes){
        bookTitle=attributes.get(ConstantValues.LIBRARY_BOOK_BOOK_TITLE_JSON_LABEL);
        bookId=attributes.get(ConstantValues.LIBRARY_BOOK_BOOK_ID_JSON_LABEL);
        author=attributes.get(ConstantValues.LIBRARY_BOOK_BOOK_AUTHOR_JSON_LABEL);
        copies=Integer.parseInt(attributes.get(ConstantValues.LIBRARY_BOOK_BOOK_COPIES_JSON_LABEL));
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

    @Override
    public void decrementCopies(){
        Database.updateCopiesOfBook(bookId,copies-1);
        --copies;
    }

    @Override
    public void incrementCopies(){
        Database.updateCopiesOfBook(bookId,copies+1);
        ++copies;
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
