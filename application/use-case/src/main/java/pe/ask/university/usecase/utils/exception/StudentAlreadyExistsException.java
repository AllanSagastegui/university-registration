package pe.ask.university.usecase.utils.exception;

import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.model.utils.exception.ErrorCatalog;

public class StudentAlreadyExistsException extends BaseException {
    public StudentAlreadyExistsException() {
        super(
                ErrorCatalog.STUDENT_ALREADY_EXISTS.getErrorCode(),
                ErrorCatalog.STUDENT_ALREADY_EXISTS.getExceptionName(),
                ErrorCatalog.STUDENT_ALREADY_EXISTS.getMessage(),
                ErrorCatalog.STUDENT_ALREADY_EXISTS.getStatus(),
                ErrorCatalog.STUDENT_ALREADY_EXISTS.getErrors()
        );
    }
}
