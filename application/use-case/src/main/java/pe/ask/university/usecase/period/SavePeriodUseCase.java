package pe.ask.university.usecase.period;

import pe.ask.university.model.period.Period;
import pe.ask.university.port.in.usecase.period.ISavePeriodUseCase;
import pe.ask.university.port.out.persistence.IPeriodRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.PeriodAlreadyExistsException;
import reactor.core.publisher.Mono;

/**
 * Use case for saving a {@link Period}.
 * <p>
 * This class implements the {@link ISavePeriodUseCase} interface and provides the
 * business logic for creating or updating a period. It interacts with the
 * {@link IPeriodRepository} to persist period data and includes validation
 * to ensure uniqueness of period names.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class SavePeriodUseCase implements ISavePeriodUseCase {

    private final IPeriodRepository repository;

    /**
     * Constructs a new {@code SavePeriodUseCase}.
     *
     * @param repository The repository for period persistence operations.
     */
    public SavePeriodUseCase(IPeriodRepository repository) {
        this.repository = repository;
    }

    /**
     * Saves a given period after validating its unique fields.
     * <p>
     * This method first validates that the period name is unique. If the name
     * already exists, it emits a {@link RuntimeException}. Otherwise, it proceeds
     * to save the period using the {@link IPeriodRepository}.
     * </p>
     *
     * @param period The {@link Period} object to be saved.
     * @return A {@link Mono} emitting the saved {@link Period}.
     */
    @Override
    public Mono<Period> savePeriod(Period period) {
        return validateUniqueFields(period)
                .then(repository.savePeriod(period));
    }

    /**
     * Validates that the period's unique fields (e.g., name) do not already exist.
     * <p>
     * This private method checks if a period with the same name already exists
     * in the repository. If a duplicate is found, it emits a {@link PeriodAlreadyExistsException}.
     * </p>
     *
     * @param period The {@link Period} object to validate.
     * @return A {@link Mono<Void>} that completes successfully if the fields are unique,
     *         or emits a {@link PeriodAlreadyExistsException} if a duplicate is found.
     */
    private Mono<Void> validateUniqueFields(Period period) {
        return repository.getPeriodByName(period.getName())
                .flatMap(found -> Mono.<Void>error(PeriodAlreadyExistsException::new))
                .then();
    }
}
