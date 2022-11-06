package org.library.Model;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.library.Impl.ConstantValues;

import java.util.Date;
import java.util.Map;

public class BookBorrow {

    @BsonProperty(value = "student_id")
    int id;

    @BsonProperty(value = "book_id")
    int book_id;

    @BsonProperty(value = "start_date")
    Date startDate;

    @BsonProperty(value = "end_date")
    Date endDate;

    public BookBorrow(){}

    public BookBorrow(int id, int book_id){
        this.id=id;
        this.book_id=book_id;
        startDate = new Date(System.currentTimeMillis());
        endDate = new Date(System.currentTimeMillis()+ 5 * ConstantValues.MILLIS_IN_A_DAY);
        Database.insertBookBorrowRecord(this);
    }

    public BookBorrow(Map<String,String> attributes){
        this.id=Integer.parseInt(attributes.get("id"));
        this.book_id=Integer.parseInt(attributes.get("book_id"));
        startDate = new Date(System.currentTimeMillis());
        if(attributes.containsKey("days"))
            endDate = new Date(System.currentTimeMillis()+ Integer.parseInt(attributes.get("days")) * ConstantValues.MILLIS_IN_A_DAY);
        else
            endDate = new Date(System.currentTimeMillis()+ 5 * ConstantValues.MILLIS_IN_A_DAY);
        System.out.println(id+" "+book_id+" "+startDate+"  "+endDate);
        Database.insertBookBorrowRecord(this);
    }

    public int getId() {
        return id;
    }

    public int getBook_id() {
        return book_id;
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
        this.book_id = book_id;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public static Iterable<BookBorrow> getBookBorrowRecords(){
        return Database.getBookBorrowRecords();
    }

    public static Iterable<BookBorrow> getDenyList(){
        return Database.getDenyListRecords();
    }
}
