package org.library.Exception;

public class BookCopiesNotAvailable extends RuntimeException{

    public BookCopiesNotAvailable(){
        super("Book is not available or copies for the selected book are not available");
    }
}
