package raffaele.u5w2d1.execptionnn;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import raffaele.u5w2d1.payload.ErrorPayload;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorsHandler {


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public ErrorPayload handlebadrequest(BadRequestException ex){
        return new ErrorPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    // Tra le parentesi specifico il tipo di eccezione che dovrà gestire questo metodo
    public ErrorPayload handleNotFoundEx(NotFoundException ex) { // Abbiamo accesso anche alla stessa eccezione
        return new ErrorPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorPayload handleGenericEx(Exception ex) {
        // Nel caso di tutti gli altri errori, mandiamo un 500 (non svelando però i dettagli di tale problema)
        ex.printStackTrace(); // E' però importante non nascondere neanche a noi stessi la causa dell'errore
        // quindi un qualcosa tipo un stack trace ci può aiutare nella risoluzione del bug
        return new ErrorPayload("C'è stato un errore lato server, giuro che lo risolveremo presto!", LocalDateTime.now());
    }
}
