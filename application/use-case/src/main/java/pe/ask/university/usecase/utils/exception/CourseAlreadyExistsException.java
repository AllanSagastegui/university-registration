package pe.ask.university.usecase.utils.exception;

import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.model.utils.exception.ErrorCatalog;

import java.util.Map;

public class CourseAlreadyExistsException extends BaseException {
    public CourseAlreadyExistsException() {
        super(
                ErrorCatalog.COURSE_ALREADY_EXISTS.getErrorCode(),
                ErrorCatalog.COURSE_ALREADY_EXISTS.getExceptionName(),
                ErrorCatalog.COURSE_ALREADY_EXISTS.getMessage(),
                ErrorCatalog.COURSE_ALREADY_EXISTS.getStatus(),
                ErrorCatalog.COURSE_ALREADY_EXISTS.getErrors()
        );
    }
}
