package pe.ask.university.usecase.utils.exception;

import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.model.utils.exception.ErrorCatalog;

public class PeriodAlreadyExistsException extends BaseException {
    public PeriodAlreadyExistsException() {
        super(
                ErrorCatalog.PERIOD_ALREADY_EXISTS.getErrorCode(),
                ErrorCatalog.PERIOD_ALREADY_EXISTS.getExceptionName(),
                ErrorCatalog.PERIOD_ALREADY_EXISTS.getMessage(),
                ErrorCatalog.PERIOD_ALREADY_EXISTS.getStatus(),
                ErrorCatalog.PERIOD_ALREADY_EXISTS.getErrors()
        );
    }
}
