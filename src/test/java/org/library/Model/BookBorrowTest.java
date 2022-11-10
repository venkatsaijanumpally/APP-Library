package org.library.Model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class BookBorrowTest {

    final String EXPECTED_BOOK_ID = "9921";
    final String EXPECTED_BOOK_TITLE = "UNIT TESTING BOOK";
    final String EXPECTED_AUTHOR = "IRODOV";
    final int EXPECTED_COPIES = 12;
    LibraryBook libraryBook;
    final int EXPECTED_ID = 8841;
    final Status EXPECTED_STATUS = Status.ALLOWED;
    final String EXPECTED_EMAIL = "abc@gmail.com";
    final String EXPECTED_PROGRAM = "AEROSPACE";
    final String EXPECTED_PHONE = "8888822222";
    final int EXPECTED_DAYS=2;
    Student student;
    BookBorrow borrowRecord;

    @Before
    public void setUp() {
        HashMap<String,String> bookMap= new HashMap<>();
        bookMap.put("book_id",EXPECTED_BOOK_ID);
        bookMap.put("author",EXPECTED_AUTHOR);
        bookMap.put("copies",String.valueOf(EXPECTED_COPIES));
        bookMap.put("book_title",EXPECTED_BOOK_TITLE);
        libraryBook=new LibraryBook(bookMap);
        HashMap<String,String> studentMap= new HashMap<>();
        studentMap.put("id",String.valueOf(EXPECTED_ID));
        studentMap.put("status",String.valueOf(EXPECTED_STATUS));
        studentMap.put("email",EXPECTED_EMAIL);
        studentMap.put("program",EXPECTED_PROGRAM);
        studentMap.put("phone",EXPECTED_PHONE);
        student=new Student(studentMap);
        HashMap<String,String> hm= new HashMap<>();
        hm.put("id",String.valueOf(EXPECTED_ID));
        hm.put("book_id",EXPECTED_BOOK_ID);
        hm.put("days", String.valueOf(EXPECTED_DAYS));
        borrowRecord=new BookBorrow(hm);
    }

    @After
    public void tearDown() {
        if(BookBorrow.checkIfStudentHasRecord(EXPECTED_ID, Integer.parseInt(EXPECTED_BOOK_ID)))
            BookBorrow.deleteRecord(EXPECTED_ID, Integer.parseInt(EXPECTED_BOOK_ID));
        if(Student.studentExist(EXPECTED_ID))
            Student.deleteStudent(student.getId());
        Database.deleteBook(EXPECTED_BOOK_ID);
    }

    @Test
    public void bookBorrowTest(){
        Assert.assertEquals(borrowRecord.getBook_id(),Integer.parseInt(EXPECTED_BOOK_ID));
        Assert.assertEquals(borrowRecord.getId(),EXPECTED_ID);
    }

    @Test
    public void testIfStudentHasRecordTest(){
        Assert.assertTrue(BookBorrow.checkIfStudentHasRecord(EXPECTED_ID));
    }

    @Test
    public void testIfStudentandBookHasRecordTest(){
        Assert.assertTrue(BookBorrow.checkIfStudentHasRecord(EXPECTED_ID, Integer.parseInt(EXPECTED_BOOK_ID)));
    }

    @Test
    public void testDeleteRecord(){
        BookBorrow.deleteRecord(EXPECTED_ID, Integer.parseInt(EXPECTED_BOOK_ID));
        Assert.assertFalse(BookBorrow.checkIfStudentHasRecord(EXPECTED_ID, Integer.parseInt(EXPECTED_BOOK_ID)));
    }
}
