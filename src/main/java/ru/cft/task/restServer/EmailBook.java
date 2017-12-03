package ru.cft.task.restServer;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailBook {
    private Map<Long, EmailRecord> book;

    public EmailBook() {
        book = new HashMap<>();
    }

    public EmailRecord addEmailRecord(long id, String name, String email) throws EmailException {
        for (Map.Entry<Long, EmailRecord> rec : book.entrySet()) {
            if (rec.getValue().getEmail() == email) {
                throw new EmailException("Запись с {email = " + email + "} уже есть в базе");
            }
        }
        EmailRecord new_rec = new EmailRecord(id, name, email);
        book.put(id, new_rec);
        return new_rec;
    }

    public boolean removeEmailRecord(long id) throws EmailException {
        if (book.containsKey(id)) {
            book.remove(id);
            return true;
        }
        throw new EmailException("Запись с {id = " + id + "} не найдена");
    }

    public int count() {
        return book.size();
    }

    public EmailRecord findRecordById(long id) throws EmailException {
        if (book.containsKey(id)) {
            return book.get(id);
        }
        throw new EmailException("Запись с {id = " + id + "} не найдена");
    }

    public EmailRecord findRecordByName(String name) throws EmailException {
        for (Map.Entry<Long, EmailRecord> rec : book.entrySet()) {
            if (rec.getValue().getName().toLowerCase() == name.toLowerCase()) {
                return rec.getValue();
            }
        }
        throw new EmailException("Запись с {name = " + name + "} не найдена");
    }

    public EmailRecord findRecordByEmail(String email) throws EmailException {
        for (Map.Entry<Long, EmailRecord> rec : book.entrySet()) {
            if (rec.getValue().getEmail().toLowerCase() == email.toLowerCase()) {
                return rec.getValue();
            }
        }
        throw new EmailException("Запись с {email = " + email + "} не найдена");
    }

    public EmailRecord editRecord(long id, String name, String email) throws EmailException {
        if (book.containsKey(id)) {
            if (!name.isEmpty()) {
                book.get(id).setName(name);
            }
            if (!email.isEmpty()) {
                book.get(id).setEmail(email);
            }
            return book.get(id);
        }
        throw new EmailException("Запись с {id = " + id + "} не найдена");
    }
}
