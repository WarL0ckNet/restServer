package ru.cft.task.restServer;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class EmailBookController {
    final static Logger log = Logger.getLogger(EmailBookController.class);
    private final AtomicLong new_id = new AtomicLong();
    private EmailBook book = new EmailBook();

    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public BookStatus emailRec(@RequestParam(value = "name", defaultValue = "John Unknown") String name,
                                @RequestParam(value = "email", defaultValue = "john@unknown.ru") String email) {
        EmailRecord rec = new EmailRecord(new_id.incrementAndGet(), name, email);
        return this.book.addRecord(rec);
    }

    //@RequestMapping(value = "/email", method = RequestMethod.DELETE)
    //public  em
}
