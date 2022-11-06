package org.library.Impl;

import java.text.SimpleDateFormat;

public class ConstantValues {
    public static final String CONNECTION_STRING = "mongodb+srv://Kub-deployment-user:Password@cluster0.nnf4l.mongodb.net/?retryWrites=true&w=majority";
    public static final SimpleDateFormat DATE_FORMAT =new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    public static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
    public static final String DATABASE_NAME="library";
    public static final String STUDENT_COLLECTION_NAME="students";
    public static final String BOOK_BORROW_COLLECTION_NAME="Book_Borrow_Records";
}
