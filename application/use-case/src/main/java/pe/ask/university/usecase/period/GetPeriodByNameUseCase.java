package pe.ask.university.usecase.period;

import pe.ask.university.model.period.Period;
import pe.ask.university.port.in.usecase.period.IGetPeriodByNameUseCase;
import pe.ask.university.port.out.persistence.IPeriodRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.PeriodNotFoundException;
import reactor.core.publisher.Mono;

/**
 * Use case for retrieving a {@link Period} by its name.
 * <p>
 * This class implements the {@link IGetPeriodByNameUseCase} interface and provides the
 * business logic for fetching a single period. It interacts with the
 * {@link IPeriodRepository} to retrieve period data.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetPeriodByNameUseCase implements IGetPeriodByNameUseCase {

    private final IPeriodRepository repository;

    /**
     * Constructs a new {@code GetPeriodByNameUseCase}.
     *
     * @param repository The repository for period persistence operations.
     */
    public GetPeriodByNameUseCase(IPeriodRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a period by its name.
     * <p>
     * This method fetches a period from the {@link IPeriodRepository} using the
     * provided name. If no period is found with the given name, it emits a
     * {@link PeriodNotFoundException}.
     * </p>
     *
     * @param name The name of the period to retrieve.
     * @return A {@link Mono} emitting the found {@link Period}.
     */
    @Override
    public Mono<Period> getPeriodByName(String name) {
        return repository.getPeriodByName(name)
                .switchIfEmpty(Mono.error(PeriodNotFoundException::new));
    }
}
