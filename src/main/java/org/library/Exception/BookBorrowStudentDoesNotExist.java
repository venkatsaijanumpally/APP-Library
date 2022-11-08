package org.library.Exception;

public class BookBorrowStudentDoesNotExist extends RuntimeException{
    public BookBorrowStudentDoesNotExist(){
        super("Failed to borrow as student with this id does not exist");
    }
}
