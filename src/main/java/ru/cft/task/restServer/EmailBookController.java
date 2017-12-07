package ru.cft.task.restServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/")
public class EmailBookController {

    private static final Logger logger = LoggerFactory.getLogger(EmailBookController.class);
    private final AtomicLong new_id = new AtomicLong();

    @Autowired
    private EmailBook emailBook;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmailRecord> addEmailRec(@RequestParam(value = "name") String name,
                                                   @RequestParam(value = "email") String email) throws EmailException {
        logger.warn("Method POST: {name = " + name + ", email = " + email + "}");
        return new ResponseEntity<EmailRecord>(emailBook.addEmailRecord(new_id.incrementAndGet(), name, email), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EmailRecord> findEmailRec(@RequestParam(value = "id", required = false) String id,
                                                    @RequestParam(value = "name", required = false) String name,
                                                    @RequestParam(value = "email", required = false) String email
    ) throws EmailException {
        logger.warn("Method GET: {id = " + id + ", name = " + name + ", email = " + email + "}");
        if (name != null && !name.isEmpty()) {
            return new ResponseEntity<EmailRecord>(emailBook.findRecordByName(name), HttpStatus.OK);
        } else if (email != null && !email.isEmpty()) {
            return new ResponseEntity<EmailRecord>(emailBook.findRecordByEmail(email), HttpStatus.OK);
        } else if (id != null && !id.isEmpty()) {
            return new ResponseEntity<EmailRecord>(emailBook.findRecordById(Long.valueOf(id)), HttpStatus.OK);
        } else {
            throw new EmailException("Задайте параметр для поиска");
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<EmailRecord> editEmailRec(@RequestParam(value = "id", required = true) String id,
                                                    @RequestParam(value = "name") String name,
                                                    @RequestParam(value = "email") String email
    ) throws EmailException {
        logger.warn("Method PUT: {id = " + id + ", name = " + name + ", email = " + email + "}");
        return new ResponseEntity<EmailRecord>(emailBook.editRecord(Long.valueOf(id), name, email), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ErrorResponse> removeEmailRec(@PathVariable(value = "id") long id) throws EmailException {
        logger.warn("Method DELETE: {id = " + id + "}");
        if (emailBook.removeEmailRecord(id)) {
            ErrorResponse message = new ErrorResponse();
            message.setErrorCode(HttpStatus.OK.value());
            message.setMessage("Запись {id = " + id + "} успешно удалена");
            return new ResponseEntity<ErrorResponse>(message, HttpStatus.OK);
        }
        return null;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int countEmailRecords() {
        logger.warn("Method GET: count");
        return emailBook.count();
    }
}
