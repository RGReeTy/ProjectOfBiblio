package by.javatr.library.service.exception;

public class ServiceException extends Exception {

    public ServiceException() {
        super("Error at Service layer");
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable e) {
        e.printStackTrace();
    }
}
