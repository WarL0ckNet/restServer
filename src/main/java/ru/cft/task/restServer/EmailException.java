package ru.cft.task.restServer;

// Класс исключения для работы
public class EmailException extends Exception {
    private static final long serialVersionUID = 1L;

    public EmailException() {
        super();
    }

    public EmailException(String message) {
        super(message);
    }
}
