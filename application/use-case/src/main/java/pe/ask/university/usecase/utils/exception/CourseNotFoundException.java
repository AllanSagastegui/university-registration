package pe.ask.university.usecase.utils.exception;

import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.model.utils.exception.ErrorCatalog;

public class CourseNotFoundException extends BaseException {
    public CourseNotFoundException() {
        super(
                ErrorCatalog.COURSE_NOT_FOUND.getErrorCode(),
                ErrorCatalog.COURSE_NOT_FOUND.getExceptionName(),
                ErrorCatalog.COURSE_NOT_FOUND.getMessage(),
                ErrorCatalog.COURSE_NOT_FOUND.getStatus(),
                ErrorCatalog.COURSE_NOT_FOUND.getErrors()
        );
    }
}
