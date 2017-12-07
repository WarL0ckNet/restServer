package ru.cft.task.restServer;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailBook {
    private Map<Long, EmailRecord> book;        // Внутренний массив записей

    public EmailBook() {
        // Массив, хешем в котором будет id записи для быстрого поиска
        book = new HashMap<>();
    }

    // Добавление записи в массив
    public EmailRecord addEmailRecord(long id, String name, String email) throws EmailException {
        // ПРоверка на уникальность email
        for (Map.Entry<Long, EmailRecord> rec : book.entrySet()) {
            if (rec.getValue().getEmail().equalsIgnoreCase(email)) {
                throw new EmailException("Запись с {email = " + email + "} уже есть в базе");
            }
        }
        EmailRecord new_rec = new EmailRecord(id, name, email);
        book.put(id, new_rec);
        return new_rec; // Возвращаем новую добавленную запись
    }

    // Удаление записи из массива
    public boolean removeEmailRecord(long id) throws EmailException {
        // Ищем по id, если не найдено бросаем исключение
        if (book.containsKey(id)) {
            book.remove(id);
            return true;
        }
        throw new EmailException("Запись с {id = " + id + "} не найдена");
    }

    // Просто считаем кол-во записей массива
    public int count() {
        return book.size();
    }

    // Поиск записи по id
    public EmailRecord findRecordById(long id) throws EmailException {
        if (book.containsKey(id)) {
            return book.get(id);
        }
        throw new EmailException("Запись с {id = " + id + "} не найдена");
    }

    // Поиск записи по имени
    public EmailRecord findRecordByName(String name) throws EmailException {
        for (Map.Entry<Long, EmailRecord> rec : book.entrySet()) {
            if (rec.getValue().getName().equalsIgnoreCase(name)) {
                return rec.getValue();
            }
        }
        throw new EmailException("Запись с {name = " + name + "} не найдена");
    }

    // Поиск записи по почте
    public EmailRecord findRecordByEmail(String email) throws EmailException {
        for (Map.Entry<Long, EmailRecord> rec : book.entrySet()) {
            if (rec.getValue().getEmail().equalsIgnoreCase(email)) {
                return rec.getValue();
            }
        }
        throw new EmailException("Запись с {email = " + email + "} не найдена");
    }

    // Изменение записи
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
