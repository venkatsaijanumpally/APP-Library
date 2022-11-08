package org.library.Exception;

public class StudentBorrowRecordExist extends RuntimeException {

    public StudentBorrowRecordExist(){
        super("Could not Delete the student since the student has a borrow record");
    }
}
