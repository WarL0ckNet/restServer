package ru.cft.task.restServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/")
public class EmailBookController {

    private final AtomicLong new_id = new AtomicLong();

    @Autowired
    private EmailBook emailBook;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmailRecord> addEmailRec(@RequestParam(value = "name", defaultValue = "John Unknown") String name,
                                                   @RequestParam(value = "email", defaultValue = "john@unknown.ru") String email) throws EmailException {
        return new ResponseEntity<EmailRecord>(emailBook.addEmailRecord(new_id.incrementAndGet(), name, email), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<EmailRecord> findEmailRec(@RequestParam(value = "id", defaultValue = "") String id,
                                                    @RequestParam(value = "name", defaultValue = "") String name,
                                                    @RequestParam(value = "email", defaultValue = "") String email
    ) throws EmailException {
        if (!name.isEmpty()) {
            return new ResponseEntity<EmailRecord>(emailBook.findRecordByName(name), HttpStatus.OK);
        } else if (!email.isEmpty()) {
            return new ResponseEntity<EmailRecord>(emailBook.findRecordByEmail(email), HttpStatus.OK);
        } else {
            return new ResponseEntity<EmailRecord>(emailBook.findRecordById(Long.valueOf(id)), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<EmailRecord> editEmailRec(@RequestParam(value = "id", required = true) long id,
                                                    @RequestParam(value = "name", defaultValue = "") String name,
                                                    @RequestParam(value = "email", defaultValue = "") String email
    ) throws EmailException {
        return new ResponseEntity<EmailRecord>(emailBook.editRecord(id, name, email), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<ErrorResponse> removeEmailRec(@RequestParam(value = "id", required = true) long id) throws EmailException {
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
        return emailBook.count();
    }

    @ExceptionHandler({EmailException.class, Exception.class})
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);

    }
}
