package by.babanin.testtask.service;

public class UserExistsException extends Exception {
    public UserExistsException() {
    }

    public UserExistsException(String message) {
        super(message);
    }
}
