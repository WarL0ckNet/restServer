package ru.cft.task.restServer;

import java.util.HashMap;

public class EmailBook {
    private HashMap<Long, EmailRecord> book;
    private BookStatus status;

    public EmailBook() {
        this.book = new HashMap();
        this.status.setStatus(true);
    }

    public BookStatus addRecord(EmailRecord rec) {
        if (!this.book.containsKey(rec.getId())) {
            this.book.put(rec.getId(), rec);
            return this.status.setMessage("Запись успешно добавлена");
        }else{
            return this.status.setError("Запись с таким id уже существует");
        }
    }

    public BookStatus removeRecord(Long id) {
        if (!this.book.containsKey(id)) {
            this.book.remove(id);
            return this.status.setMessage("Запись успешно удалена");
        }else{
            return this.status.setError("Записи с таким id не существует");
        }
    }

    public int count() {
        return this.book.size();
    }
}
