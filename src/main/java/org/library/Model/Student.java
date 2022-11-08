package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.library.Impl.ConstantValues;
import org.library.Exception.StudentBorrowRecordExist;
import org.library.Exception.StudentDoesNotExist;
import org.library.Exception.StudentExistException;

import java.util.Map;

public class Student {

    @BsonProperty(value = ConstantValues.STUDENT_ID_LABEL)
    int id;

    @BsonProperty(value = ConstantValues.STUDENT_STATUS_LABEL)
    private Status status;

    @BsonProperty(value = ConstantValues.STUDENT_EMAIL_LABEL)
    private String email;

    @BsonProperty(value = ConstantValues.STUDENT_PROGRAM_LABEL)
    private String program;

    @BsonProperty(value = ConstantValues.STUDENT_PHONE_LABEL)
    private String phone;

    public Student(){}

    public Student(int id, Status status, String email, String program, String phone){
        this.id = id;
        this.status=status;
        this.email=email;
        this.program=program;
        this.phone=phone;
        Database.insertStudent(this.id,status,email,program,phone);
    }

    public Student(Map<String,String> attributes) {
        id =Integer.parseInt(attributes.get("id"));
        if(Database.studentExist(id))
            throw new StudentExistException();
        this.status= Status.valueOf(attributes.get("status"));
        this.email=attributes.get("email");
        this.program=attributes.get("program");
        this.phone=attributes.get("phone");
        Database.insertStudent(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public static void loanBooks(int id, int book){
        Database.insertBookBorrowRecord(id,book);
    }

    public static Iterable<Student> getStudents() {
        return Database.getListOfStudent();
    }

    public static Student deleteStudent(int id) {
        if(!studentExist(id))
            throw new StudentDoesNotExist();
        else if(BookBorrow.checkIfStudentHasRecord(id))
            throw new StudentBorrowRecordExist();
        return Database.deleteStudent(id);
    }

    public static boolean studentExist(int id){
        return Database.studentExist(id);
    }
}
