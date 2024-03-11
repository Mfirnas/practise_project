package com.ey.dt.masterdata.exception;

import com.ey.dt.masterdata.payload.response.common.ErrorResponseDTO;
import com.ey.dt.masterdata.utility.MessageResource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageResource messageResource;

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors = ex
                .getBindingResult()
                .getFieldErrors();

        String error = fieldErrors.get(0).getField() + ": " + messageResource.getMessage(fieldErrors.get(0).getDefaultMessage());

        return new ResponseEntity<>(new ErrorResponseDTO<>(HttpStatus.BAD_REQUEST, error,
                LocalDateTime.now(),
                request.getDescription(false)), status);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO<>(HttpStatus.UNAUTHORIZED, ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO<>(HttpStatus.NOT_FOUND, ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommonServerException.class)
    public final ResponseEntity<Object> handleCommonServerException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataConflictException.class)
    public final ResponseEntity<Object> handleDataConflictException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO<>(HttpStatus.CONFLICT, ex.getMessage(),
                LocalDateTime.now(),
                request.getDescription(false)), HttpStatus.CONFLICT);
    }
}
