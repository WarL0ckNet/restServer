package ru.cft.task.restServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmailBookController {

    @Autowired
    private EmailBook emailBook;

    @RequestMapping(method = RequestMethod.POST)
    public long addEmailRec(@RequestParam(value = "name", defaultValue = "John Unknown") String name,
                            @RequestParam(value = "email", defaultValue = "john@unknown.ru") String email) {
        return emailBook.addEmailRecord(name, email);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean removeEmailRec(@RequestParam(value = "id", required = true) long id) {
        return emailBook.removeEmailRecord(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public EmailRecord findEmailRec(@RequestParam(value = "id", required = true) long id) {
        return emailBook.findRec(id);
    }
}
