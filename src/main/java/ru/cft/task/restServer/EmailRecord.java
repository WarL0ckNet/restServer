package ru.cft.task.restServer;

// Класс записи
public class EmailRecord {
    private long id;        // id записи
    private String name;    // Имя
    private String email;   // почта

    public EmailRecord(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Rec:" + name + "<" + email + ">";
    }
}
