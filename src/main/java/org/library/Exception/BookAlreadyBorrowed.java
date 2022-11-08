package org.library.Exception;

public class BookAlreadyBorrowed extends RuntimeException {
    public BookAlreadyBorrowed(){
        super("Book already borrowed");
    }
}
