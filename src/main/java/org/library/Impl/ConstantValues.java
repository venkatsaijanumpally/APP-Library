package org.library.Impl;

import java.text.SimpleDateFormat;

public class ConstantValues {

    public static final int SERVER_PORT = 8000;
    public static final long SCHEDULED_UPDATE_INTERVAL = 600;

    public static final String CONNECTION_STRING = "mongodb+srv://Kub-deployment-user:Password@cluster0.nnf4l.mongodb.net/?retryWrites=true&w=majority";
    public static final String DATABASE_NAME="library";

    public static final String BOOK_BORROW_COLLECTION_NAME="Book_Borrow_Records2";
    public static final String BOOK_BORROW_STUDENT_ID_LABEL="student_id";
    public static final String BOOK_BORROW_BOOK_ID_LABEL="book_id";
    public static final String BOOK_BORROW_START_DATE_LABEL = "start_date";
    public static final String BOOK_BORROW_END_DATE_LABEL = "end_date";
    public static final SimpleDateFormat BOOK_BORROW_DATE_FORMAT =new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    public static final long BOOK_BORROW_MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
    public static final String BOOK_BORROW_STUDENT_ID_JSON_LABEL = "id";
    public static final String BOOK_BORROW_BOOK_ID_JSON_LABEL = "book_id";
    public static final String BOOK_BORROW_DAYS_JSON_LABEL = "days";

    public static final String STUDENT_COLLECTION_NAME="students2";
    public static final String STUDENT_ID_LABEL="student_id";
    public static final String STUDENT_STATUS_LABEL="status";
    public static final String STUDENT_EMAIL_LABEL="email";
    public static final String STUDENT_PROGRAM_LABEL="program";
    public static final String STUDENT_PHONE_LABEL="phone";
    public static final String STUDENT_DUE_AMOUNT_LABEL="due_amount";
    public static final String STUDENT_ID_JSON_LABEL="id";
    public static final String STUDENT_STATUS_JSON_LABEL="status";
    public static final String STUDENT_EMAIL_JSON_LABEL="email";
    public static final String STUDENT_PROGRAM_JSON_LABEL="program";
    public static final String STUDENT_PHONE_JSON_LABEL="phone";


    public static final String LIBRARY_BOOK_BOOK_TITLE_LABEL="book_title";
    public static final String LIBRARY_BOOK_BOOK_ID_LABEL="book_id";
    public static final String LIBRARY_BOOK_BOOK_AUTHOR_LABEL="author";
    public static final String LIBRARY_BOOK_BOOK_COPIES_LABEL="copies";
    public static final String LIBRARY_BOOK_BOOK_TITLE_JSON_LABEL="book_title";
    public static final String LIBRARY_BOOK_BOOK_ID_JSON_LABEL="book_id";
    public static final String LIBRARY_BOOK_BOOK_AUTHOR_JSON_LABEL="author";
    public static final String LIBRARY_BOOK_BOOK_COPIES_JSON_LABEL="copies";

    public static final String ERROR_MESSAGE_STUDENT_NOT_EXIST = "Could not delete as the student with this id does not exist";

    public static final String BOOK_COLLECTION = "Books";
    public static final String LIBRARY_BOOK_ID_LABEL = "book_id";
    public static final String PRICING_STRATEGY_COLLECTION = "Pricing_Strategy";
}
