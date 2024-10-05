package org.example.noteCollector_V2.exception;

import java.nio.file.attribute.UserPrincipalNotFoundException;

public class DataPersistException extends RuntimeException {

    public DataPersistException(String message) {
        super(message);
    }

    public DataPersistException(UserPrincipalNotFoundException e) {
        super(e.getMessage());
    }

    public DataPersistException(String message, Throwable cause) {
        super(message, cause);
    }
}
