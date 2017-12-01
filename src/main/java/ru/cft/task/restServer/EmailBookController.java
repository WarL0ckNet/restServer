package ru.cft.task.restServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EmailBookController {
    private final AtomicLong new_id = new AtomicLong();

    @Autowired
    private EmailBook emailBook;

    @RequestMapping(method = RequestMethod.POST)
    public EmailRecord addEmailRec(@RequestParam(value = "name", defaultValue = "John Unknown") String name,
                                   @RequestParam(value = "email", defaultValue = "john@unknown.ru") String email) {
        EmailRecord rec = new EmailRecord(new_id.incrementAndGet(), name, email);
        return rec;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean removeEmailRec(@RequestParam(value = "id", required = true) Long id) {
        return true;
    }

    @RequestMapping(method = RequestMethod.GET)
    public int sizeBook() {
        return 1;
    }
}
