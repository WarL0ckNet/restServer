package ru.cft.task.restServer;

public class BookStatus {
    private boolean status;
    private String message;

    public String getMessage() {
        return message;
    }

    public BookStatus setMessage(String message) {
        this.message = message;
        this.status = true;
        return this;
    }

    public BookStatus setError(String message) {
        this.message = message;
        this.status = false;
        return this;
    }

    public boolean isValid() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
