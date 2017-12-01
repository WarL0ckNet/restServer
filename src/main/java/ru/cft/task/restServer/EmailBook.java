package ru.cft.task.restServer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class EmailBook {
    private List<EmailRecord> book;
    private final AtomicLong new_id = new AtomicLong();

    public EmailBook() {
        book = new ArrayList<>();
    }

    public long addEmailRecord(String name, String email) {
        long id = new_id.incrementAndGet();
        book.add(new EmailRecord(id, name, email));
        return id;
    }

    public boolean removeEmailRecord(long id) {
        for (EmailRecord rec : book) {
            if (rec.getId() == id) {
                book.remove(rec);
                return true;
            }
        }
        return false;
    }

    public int count() {
        return book.size();
    }

    public EmailRecord findRec(long id) {
        for (EmailRecord rec : book) {
            if (rec.getId() == id) {
                book.remove(rec);
                return rec;
            }
        }
        return null;
    }
}
