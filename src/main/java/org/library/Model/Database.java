package org.library.Model;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.library.Impl.ConstantValues;
import org.library.Impl.DatabaseClient;
import static com.mongodb.client.model.Updates.*;

import java.util.Date;
import java.util.List;

public class Database {

    public static final MongoClient MONGO_CLIENT = DatabaseClient.getInstance();
    public static final MongoDatabase LIBRARY_DB = MONGO_CLIENT.getDatabase(ConstantValues.DATABASE_NAME);
    public static final MongoCollection<Student> STUDENT_COLLECTION = LIBRARY_DB.getCollection(ConstantValues.STUDENT_COLLECTION_NAME, Student.class);
    public static final MongoCollection<BookBorrow> BOOK_BORROW_RECORDS = LIBRARY_DB.getCollection(ConstantValues.BOOK_BORROW_COLLECTION_NAME, BookBorrow.class);
    public static final MongoCollection<LibraryBook> BOOK_COLLECTION = LIBRARY_DB.getCollection(ConstantValues.BOOK_COLLECTION, LibraryBook.class);
    public static final MongoCollection<PricingStrategy> PRICING_STRATEGY_COLLECTION = LIBRARY_DB.getCollection(ConstantValues.PRICING_STRATEGY_COLLECTION, PricingStrategy.class);

    public static void insertStudent(int id, Status status, String email, String program, String phone){
        MongoCollection<Document> StudentCollection= LIBRARY_DB.getCollection("students_1");
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
                .append("Date",ConstantValues.BOOK_BORROW_DATE_FORMAT.format(new Date(System.currentTimeMillis())))
                .append("Deadline",ConstantValues.BOOK_BORROW_DATE_FORMAT.format(new Date(System.currentTimeMillis()+ 5 * ConstantValues.BOOK_BORROW_MILLIS_IN_A_DAY)));

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
        Bson lessThanCurrentDate=lt(ConstantValues.BOOK_BORROW_END_DATE_LABEL, new Date(System.currentTimeMillis()));
        return BOOK_BORROW_RECORDS.find(lessThanCurrentDate);
    }

    public static boolean studentExist(int id) {
        Bson equalComparator=eq(ConstantValues.STUDENT_ID_LABEL,id);
        return STUDENT_COLLECTION.find(equalComparator).first()!=null;
    }

    public static Student deleteStudent(int id) {
        Bson equalComparator=eq(ConstantValues.STUDENT_ID_LABEL,id);
        return STUDENT_COLLECTION.findOneAndDelete(equalComparator);
    }

    public static Iterable<BookBorrow> allBorrowedBookRecordsOfAStudent(int id){
        Bson equalComparator=eq(ConstantValues.BOOK_BORROW_STUDENT_ID_LABEL,id);
        return BOOK_BORROW_RECORDS.find(equalComparator);
    }

    public static boolean checkIfStudentBorrowed(int id) {
        Bson equalComparator=eq(ConstantValues.BOOK_BORROW_STUDENT_ID_LABEL,id);
        return BOOK_BORROW_RECORDS.find(equalComparator).first()!=null;
    }

    public static boolean checkIfStudentBorrowed(int id, int book_id) {
        Bson equalComparator=and(eq(ConstantValues.BOOK_BORROW_STUDENT_ID_LABEL,id),eq(ConstantValues.BOOK_BORROW_BOOK_ID_LABEL,book_id));
        return BOOK_BORROW_RECORDS.find(equalComparator).first()!=null;
    }

    public static BookBorrow deleteBookBorrowRecord(int id, int book_id) {
        Bson equalComparator=and(eq(ConstantValues.BOOK_BORROW_STUDENT_ID_LABEL,id),eq(ConstantValues.BOOK_BORROW_BOOK_ID_LABEL,book_id));
        BookBorrow deletedRecord=Database.BOOK_BORROW_RECORDS.findOneAndDelete(equalComparator);
        deletedRecord.book.incrementCopies();
        return deletedRecord;
    }

    public static void addBook(LibraryBook libraryBook) {
        BOOK_COLLECTION.insertOne(libraryBook);
    }

    public static LibraryBook getBook(int book_id) {
        Bson equalComparator=eq(ConstantValues.LIBRARY_BOOK_ID_LABEL,String.valueOf(book_id));
        return BOOK_COLLECTION.find(equalComparator).first();
    }

    public static Iterable<LibraryBook> getBooks() {
        return BOOK_COLLECTION.find();
    }

    public static void updateCopiesOfBook(String bookId, int updatedCopies) {
        Bson equalComparator=eq(ConstantValues.LIBRARY_BOOK_ID_LABEL,String.valueOf(bookId));
        Bson set=set(ConstantValues.LIBRARY_BOOK_BOOK_COPIES_LABEL,updatedCopies);
        BOOK_COLLECTION.updateOne(equalComparator,set);
    }

    public static PricingStrategy getPricingStrategy() {
        return PRICING_STRATEGY_COLLECTION.find().first();
    }

    public static void updatePricingStrategy(PricingStrategy pricingStrategy) {
        PRICING_STRATEGY_COLLECTION.deleteMany(new BasicDBObject());
        PRICING_STRATEGY_COLLECTION.insertOne(pricingStrategy);
    }

    public static void updateStudentDue(Student student) {
        Bson equalComparator=eq(ConstantValues.STUDENT_ID_LABEL,student.getId());
        Bson set=set(ConstantValues.STUDENT_DUE_AMOUNT_LABEL, student.getDue_amount());
        STUDENT_COLLECTION.updateOne(equalComparator,set);
    }

    public static void deleteBook(String book_id){
        Bson equalComparator=eq(ConstantValues.LIBRARY_BOOK_ID_LABEL,book_id);
        BOOK_COLLECTION.deleteOne(equalComparator);
    }
}
