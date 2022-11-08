package org.library.Exception;

public class BookBorrowRecordDoesNotExist extends RuntimeException{

    public BookBorrowRecordDoesNotExist(){
        super("Book borrow record does not exist");
    }
}
