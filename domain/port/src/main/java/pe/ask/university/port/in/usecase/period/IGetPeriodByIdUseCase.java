package pe.ask.university.port.in.usecase.period;

import pe.ask.university.model.period.Period;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Defines the input port for retrieving a period by its ID.
 * <p>
 * This functional interface represents a use case for fetching a single {@link Period}
 * based on its unique identifier. Implementations will handle the logic for
 * querying and returning the requested period.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetPeriodByIdUseCase {

    /**
     * Retrieves a period by its unique identifier.
     *
     * @param id The UUID of the period to retrieve.
     * @return A {@link Mono} emitting the found {@link Period}, or an empty Mono if not found.
     */
    Mono<Period> getPeriodById(UUID id);
}
