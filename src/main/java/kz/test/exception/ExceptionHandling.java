package kz.test.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import javax.persistence.NoResultException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandling {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";

    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
    public ResponseEntity<HttpResponseException> internalServerErrorException(UnsupportedMediaTypeStatusException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponseException> internalServerErrorException(Exception exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponseException> internalServerErrorException(IOException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponseException> notFoundException(NoResultException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    private ResponseEntity<HttpResponseException> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponseException(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }
}
