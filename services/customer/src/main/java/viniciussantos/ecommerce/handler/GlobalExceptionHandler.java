package viniciussantos.ecommerce.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import viniciussantos.ecommerce.exception.CustomerNotFoundException;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handler(CustomerNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handler(MethodArgumentNotValidException e) {
       var erros = new HashMap<String, String>();
       e.getBindingResult().getAllErrors()
               .forEach(error -> {
           var fieldName = ((FieldError)error).getField();
           var errorMessage = error.getDefaultMessage();
           erros.put(fieldName, errorMessage);
       });

       return ResponseEntity
               .status(HttpStatus.BAD_GATEWAY)
               .body(new ErrorResponse(erros));
    }
}
