package org.library.Exception;

import org.library.Impl.ConstantValues;

public class StudentDoesNotExist extends RuntimeException{

    public StudentDoesNotExist() {
        super(ConstantValues.ERROR_MESSAGE_STUDENT_NOT_EXIST);
    }
}
