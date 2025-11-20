package pe.ask.university.usecase.utils.exception;

import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.model.utils.exception.ErrorCatalog;

public class EnrollmentNotFoundException extends BaseException {
    public EnrollmentNotFoundException() {
        super(
                ErrorCatalog.ENROLLMENT_NOT_FOUND.getErrorCode(),
                ErrorCatalog.ENROLLMENT_NOT_FOUND.getExceptionName(),
                ErrorCatalog.ENROLLMENT_NOT_FOUND.getMessage(),
                ErrorCatalog.ENROLLMENT_NOT_FOUND.getStatus(),
                ErrorCatalog.ENROLLMENT_NOT_FOUND.getErrors()
        );
    }
}
