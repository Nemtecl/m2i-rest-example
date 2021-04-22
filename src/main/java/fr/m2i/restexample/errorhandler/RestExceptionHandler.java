package fr.m2i.restexample.errorhandler;

import fr.m2i.restexample.exceptions.M2I400Exception;
import fr.m2i.restexample.exceptions.M2I404Exception;
import fr.m2i.restexample.exceptions.M2I500Exception;
import fr.m2i.restexample.exceptions.M2IException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        return buildResponseEntity(new ErrorDto(""), status);
    }

    private ResponseEntity<Object> buildResponseEntity(final ErrorDto errorDto, final HttpStatus status) {
        return new ResponseEntity<>(errorDto, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleException(final IllegalArgumentException ex) {
        ErrorDto errorDto = new ErrorDto("IAE");
        log.error("IAE", ex);
        return buildResponseEntity(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(M2I404Exception.class)
    protected ResponseEntity<Object> handleException(final M2I404Exception ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(errorDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(M2I500Exception.class)
    protected ResponseEntity<Object> handleException(final M2I500Exception ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(M2I400Exception.class)
    protected ResponseEntity<Object> handleException(final M2I400Exception ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return buildResponseEntity(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(M2IException.class)
    protected ResponseEntity<Object> handleException(final M2IException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage());
        return buildResponseEntity(errorDto, HttpStatus.MULTI_STATUS);
    }
}
