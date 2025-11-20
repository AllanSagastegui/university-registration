package pe.ask.university.usecase.utils.exception;

import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.model.utils.exception.ErrorCatalog;

public class StudentNotFoundException extends BaseException {
    public StudentNotFoundException() {
        super(
                ErrorCatalog.STUDENT_NOT_FOUND.getErrorCode(),
                ErrorCatalog.STUDENT_NOT_FOUND.getExceptionName(),
                ErrorCatalog.STUDENT_NOT_FOUND.getMessage(),
                ErrorCatalog.STUDENT_NOT_FOUND.getStatus(),
                ErrorCatalog.STUDENT_NOT_FOUND.getErrors()
        );
    }
}
