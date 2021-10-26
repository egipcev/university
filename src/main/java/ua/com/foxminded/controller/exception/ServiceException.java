package ua.com.foxminded.controller.exception;

public class ServiceException extends Exception {

    public ServiceException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

}
