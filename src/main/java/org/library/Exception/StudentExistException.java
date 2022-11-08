package org.library.Exception;

public class StudentExistException extends RuntimeException{
    public StudentExistException(){
        super("Student with this Id already exist");
    }
}
