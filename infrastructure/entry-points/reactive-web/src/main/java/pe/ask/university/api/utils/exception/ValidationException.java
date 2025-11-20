package pe.ask.university.api.utils.exception;

import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.model.utils.exception.ErrorCatalog;

import java.util.Map;

public class ValidationException extends BaseException {

    public ValidationException(Map<String, String> errors) {
        super(
                ErrorCatalog.VALIDATION_EXCEPTION.getErrorCode(),
                ErrorCatalog.VALIDATION_EXCEPTION.getExceptionName(),
                ErrorCatalog.VALIDATION_EXCEPTION.getMessage(),
                ErrorCatalog.VALIDATION_EXCEPTION.getStatus(),
                errors
        );
    }
}