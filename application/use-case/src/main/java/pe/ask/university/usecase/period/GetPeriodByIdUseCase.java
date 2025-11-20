package pe.ask.university.usecase.period;

import pe.ask.university.model.period.Period;
import pe.ask.university.port.in.usecase.period.IGetPeriodByIdUseCase;
import pe.ask.university.port.out.persistence.IPeriodRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.PeriodNotFoundException;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Use case for retrieving a {@link Period} by its unique identifier.
 * <p>
 * This class implements the {@link IGetPeriodByIdUseCase} interface and provides the
 * business logic for fetching a single period. It interacts with the
 * {@link IPeriodRepository} to retrieve period data.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetPeriodByIdUseCase implements IGetPeriodByIdUseCase {

    private final IPeriodRepository repository;

    /**
     * Constructs a new {@code GetPeriodByIdUseCase}.
     *
     * @param repository The repository for period persistence operations.
     */
    public GetPeriodByIdUseCase(IPeriodRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a period by its unique identifier.
     * <p>
     * This method fetches a period from the {@link IPeriodRepository} using the
     * provided ID. If no period is found with the given ID, it emits a
     * {@link PeriodNotFoundException}.
     * </p>
     *
     * @param id The UUID of the period to retrieve.
     * @return A {@link Mono} emitting the found {@link Period}.
     */
    @Override
    public Mono<Period> getPeriodById(UUID id) {
        return repository.getPeriodById(id)
                .switchIfEmpty(Mono.error(PeriodNotFoundException::new));
    }
}
