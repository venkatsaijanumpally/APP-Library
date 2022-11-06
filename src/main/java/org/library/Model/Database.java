package org.library.Model;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.library.Impl.ConstantValues;
import org.library.Impl.DatabaseClient;

import java.util.Date;

public class Database {

    public static final MongoClient MONGO_CLIENT = DatabaseClient.getInstance();
    public static final MongoDatabase LIBRARY_DB = MONGO_CLIENT.getDatabase(ConstantValues.DATABASE_NAME);
    public static final MongoCollection<Student> STUDENT_COLLECTION = LIBRARY_DB.getCollection(ConstantValues.STUDENT_COLLECTION_NAME, Student.class);
    public static final MongoCollection<BookBorrow> BOOK_BORROW_RECORDS = LIBRARY_DB.getCollection(ConstantValues.BOOK_BORROW_COLLECTION_NAME, BookBorrow.class);

    public static void insertStudent(int id, Status status, String email, String program, String phone){
        MongoCollection<Document> StudentCollection= LIBRARY_DB.getCollection("students");
        Document studentEntry=new Document("_id", new ObjectId());
        studentEntry.append("id",id)
                .append("Status",status.toString())
                .append("Email",email)
                .append("Program",program)
                .append("Phone",phone);
        StudentCollection.insertOne(studentEntry);
    }

    public static void insertStudent(Student student){
        STUDENT_COLLECTION.insertOne(student);
    }

    public static void insertBookBorrowRecord(int id, int BookId){
        MongoCollection<Document> BorrowRecordsCollection= LIBRARY_DB.getCollection("Book_Borrow_Records");
        Document loanEntry=new Document("_id", new ObjectId());
        loanEntry.append("id",id)
                .append("Book",BookId)
                .append("Date",ConstantValues.DATE_FORMAT.format(new Date(System.currentTimeMillis())))
                .append("Deadline",ConstantValues.DATE_FORMAT.format(new Date(System.currentTimeMillis()+ 5 * ConstantValues.MILLIS_IN_A_DAY)));

        BorrowRecordsCollection.insertOne(loanEntry);
    }


    public static void insertBookBorrowRecord(BookBorrow bookBorrow) {
        BOOK_BORROW_RECORDS.insertOne(bookBorrow);
    }

    public static Iterable<Student> getListOfStudent() {
        return STUDENT_COLLECTION.find();
    }

    public static Iterable<BookBorrow> getBookBorrowRecords() {
        return BOOK_BORROW_RECORDS.find();
    }

    public static Iterable<BookBorrow> getDenyListRecords(){
        Bson lessThanCurrentDate=lt("end_date",new Date(System.currentTimeMillis()));
        return BOOK_BORROW_RECORDS.find(lessThanCurrentDate);
    }
}
