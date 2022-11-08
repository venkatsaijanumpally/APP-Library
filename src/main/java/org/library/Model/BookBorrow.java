package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.library.Exception.*;
import org.library.Impl.*;

import java.util.Date;
import java.util.Map;

public class BookBorrow {

    @BsonProperty(value = ConstantValues.BOOK_BORROW_STUDENT_ID_LABEL)
    int id;

    @BsonIgnore
    LibraryBook book;

    @BsonProperty(value = ConstantValues.BOOK_BORROW_BOOK_ID_LABEL)
    int book_id;

    @BsonProperty(value = ConstantValues.BOOK_BORROW_START_DATE_LABEL)
    Date startDate;

    @BsonProperty(value = ConstantValues.BOOK_BORROW_END_DATE_LABEL)
    Date endDate;

    public BookBorrow(){}

    public BookBorrow(Map<String,String> attributes){
        this.id=Integer.parseInt(attributes.get(ConstantValues.BOOK_BORROW_STUDENT_ID_JSON_LABEL));
        this.book_id=Integer.parseInt(attributes.get(ConstantValues.BOOK_BORROW_BOOK_ID_JSON_LABEL));
        book = Catalog.getBook(book_id);
        validateBookBorrowAttributes(book,book_id,id);
        book.decrementCopies();
        startDate = new Date(System.currentTimeMillis());
        if(attributes.containsKey(ConstantValues.BOOK_BORROW_DAYS_JSON_LABEL))
            endDate = new Date(System.currentTimeMillis()+ Math.max(1,Integer.parseInt(attributes.get(ConstantValues.BOOK_BORROW_DAYS_JSON_LABEL))) * ConstantValues.BOOK_BORROW_MILLIS_IN_A_DAY);
        else
            endDate = new Date(System.currentTimeMillis()+ 5 * ConstantValues.BOOK_BORROW_MILLIS_IN_A_DAY);
        Database.insertBookBorrowRecord(this);
    }

    private void validateBookBorrowAttributes(LibraryBook book, int book_id, int id) {
        System.out.println(book);
        if(!Student.studentExist(id)) throw new BookBorrowStudentDoesNotExist();
        if(book==null || !book.available()) throw new BookCopiesNotAvailable();
        if(checkIfStudentHasRecord(id,book_id)) throw new BookAlreadyBorrowed();
    }

    public int getId() {
        return id;
    }

    public int getBook_id() {
        return book_id;
    }

    public LibraryBook retrieveBook() {
        return book;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBook_id(int book_id) {
        book = Database.getBook(book_id);
        this.book_id = book_id;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setBook(LibraryBook book) {
        this.book = book;
    }

    public static boolean checkIfStudentHasRecord(int id){
        return Database.checkIfStudentBorrowed(id);
    }

    public static boolean checkIfStudentHasRecord(int id, int book_id){
        return Database.checkIfStudentBorrowed(id,book_id);
    }

    public static Iterable<BookBorrow> getBookBorrowRecords(){
        return Database.getBookBorrowRecords();
    }

    public static BookBorrow deleteRecord(int id, int book_id){
        if(!Student.studentExist(id))
            throw new StudentDoesNotExist();
        else if(!checkIfStudentHasRecord(id,book_id))
            throw new BookBorrowRecordDoesNotExist();
        return Database.deleteBookBorrowRecord(id,book_id);
    }

    public static Iterable<BookBorrow> getDenyList(){
        return Database.getDenyListRecords();
    }
}
