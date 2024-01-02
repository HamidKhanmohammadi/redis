package ir.happx.redis.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public
class BanooExeptionHandler {
    @ControllerAdvice
    public class CoreExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value = BanooExeption.class)
        public ResponseEntity<Object> exception(BanooExeption exception) {
            BanooExeptionResponsEntity exceptionResponseEntity = new BanooExeptionResponsEntity(exception);
            return new ResponseEntity<>(exceptionResponseEntity, exception.getHttpStatus());
        }
    }
}
