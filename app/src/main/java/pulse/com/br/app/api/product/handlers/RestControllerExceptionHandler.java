package pulse.com.br.app.api.product.handlers;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pulse.com.br.app.api.product.dtos.ErrorResponse;
import pulse.com.br.app.api.product.dtos.ValidationErrorResponse;
import pulse.com.br.app.core.exceptions.ModelNotFoundException;
import pulse.com.br.app.core.exceptions.ProductAlreadyExistException;
import pulse.com.br.app.core.exceptions.UserAlreadyExistException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final SnakeCaseStrategy snakeCaseStrategy = new SnakeCaseStrategy();


    /**
     * Handle ModelNotFoundException by returning a 404 Not Found response.
     *
     * @param exception the exception that was thrown.
     * @param request the current web request.
     * @return a ResponseEntity containing an ErrorResponse with details about the exception.
     */

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(
            ModelNotFoundException exception, WebRequest request
    ) {
        var status = HttpStatus.NOT_FOUND;
        var body = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(exception.getLocalizedMessage())
                .cause(exception.getClass().getSimpleName())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<ErrorResponse>(body, status);
    }


    /**
     * Handle ProductAlreadyExistException by returning a 409 Conflict response.
     *
     * @param exception the exception that was thrown.
     * @param request the current web request.
     * @return a ResponseEntity containing an ErrorResponse with details about the exception.
     */

    @ExceptionHandler(ProductAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(
            ProductAlreadyExistException exception, WebRequest request
    ) {
        var status = HttpStatus.CONFLICT;
        var body = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(exception.getLocalizedMessage())
                .cause(exception.getClass().getSimpleName())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<ErrorResponse>(body, status);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(
            UserAlreadyExistException exception, WebRequest request
    ) {
        var status = HttpStatus.CONFLICT;
        var body = ErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message(exception.getLocalizedMessage())
                .cause(exception.getClass().getSimpleName())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<ErrorResponse>(body, status);
    }

    /**
     * Handle MethodArgumentNotValidException by returning a 400 Bad Request response with validation error details.
     *
     * @param ex the exception that was thrown.
     * @param headers the HTTP headers.
     * @param statusCode the HTTP status code.
     * @param request the current web request.
     * @return a ResponseEntity containing a ValidationErrorResponse with details about the validation errors.
     */

    @Override
    @Nullable
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {
        var status = (HttpStatus) statusCode;
        var errors = new HashMap<String, List<String>>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            var fieldName = snakeCaseStrategy.translate(error.getField());
            var errorMessage = error.getDefaultMessage();
            if (errors.containsKey(fieldName)) {
                errors.get(fieldName).add(errorMessage);
            } else {
                errors.put(fieldName, new ArrayList<String>(Arrays.asList(errorMessage)));
            }
        });
        var body = ValidationErrorResponse.builder()
                .status(status.value())
                .error(status.getReasonPhrase())
                .message("Validation Error")
                .cause(ex.getClass().getSimpleName())
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();
        return new ResponseEntity<Object>(body, status);
    }
}