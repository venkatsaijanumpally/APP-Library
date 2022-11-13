package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.library.Exception.StudentBorrowRecordExist;
import org.library.Exception.StudentDoesNotExist;
import org.library.Exception.StudentExistException;
import org.library.Impl.ConstantValues;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @BsonProperty(value = ConstantValues.STUDENT_DUE_AMOUNT_LABEL)
    private int due_amount;

    public Student(){}

    public Student(Map<String,String> attributes) {
        id =Integer.parseInt(attributes.get(ConstantValues.STUDENT_ID_JSON_LABEL));
        if(Database.studentExist(id))
            throw new StudentExistException();
        this.status= Status.valueOf(attributes.get(ConstantValues.STUDENT_STATUS_JSON_LABEL));
        this.email=attributes.get(ConstantValues.STUDENT_EMAIL_JSON_LABEL);
        this.program=attributes.get(ConstantValues.STUDENT_PROGRAM_JSON_LABEL);
        this.phone=attributes.get(ConstantValues.STUDENT_PHONE_JSON_LABEL);
        due_amount=0;
        Database.insertStudent(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        //updateDueAmount();
        //Database.updateStudentDue(this);
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

    public int getDue_amount() {
        return due_amount;
    }

    public void setDue_amount(int due_amount) {
        this.due_amount = due_amount;
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

    public void updateDueAmount() {
        Iterable<BookBorrow> records=Database.allBorrowedBookRecordsOfAStudent(id);
        PricingStrategy pricingStrategy=Catalog.getPricingStrategy();
        Date dateAfter=new Date(System.currentTimeMillis());
        int initialDue=due_amount;
        int due=0;
        for(BookBorrow record: records){
            Date dateBefore=record.getEndDate();
            long dateBeforeInMs = dateBefore.getTime();
            long dateAfterInMs = dateAfter.getTime();
            if(dateAfterInMs>dateBeforeInMs){
                long timeDiff = dateAfterInMs - dateBeforeInMs;
                long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
                due+=daysDiff;
            }
        }
        due_amount=pricingStrategy.getPerDayCost()*due;
        if(initialDue!=due_amount)
            Database.updateStudentDue(this);
    }
}
