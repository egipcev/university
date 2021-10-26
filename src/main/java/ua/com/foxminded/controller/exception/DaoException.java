package ua.com.foxminded.controller.exception;

public class DaoException extends Exception {

    public DaoException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

}
