package pe.ask.university.usecase.utils.exception;

import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.model.utils.exception.ErrorCatalog;

public class PeriodNotFoundException extends BaseException {
    public PeriodNotFoundException() {
        super(
                ErrorCatalog.PERIOD_NOT_FOUND.getErrorCode(),
                ErrorCatalog.PERIOD_NOT_FOUND.getExceptionName(),
                ErrorCatalog.PERIOD_NOT_FOUND.getMessage(),
                ErrorCatalog.PERIOD_NOT_FOUND.getStatus(),
                ErrorCatalog.PERIOD_NOT_FOUND.getErrors()
        );
    }
}
