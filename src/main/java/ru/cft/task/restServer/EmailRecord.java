package ru.cft.task.restServer;

public class EmailRecord {
    private long id;
    private String name;
    private String email;

    public EmailRecord(long id, String name, String email) {
        setId(id);
        setName(name);
        setEmail(email);
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

    public String toString() {
        return String.valueOf(getId()).concat(": ").concat(getName()).concat(" - ").concat(getEmail());
    }
}
