package pe.ask.university.port.in.usecase.period;

import pe.ask.university.model.period.Period;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for retrieving a period by its name.
 * <p>
 * This functional interface represents a use case for fetching a single {@link Period}
 * based on its name. Implementations will handle the logic for querying and
 * returning the requested period.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetPeriodByNameUseCase {

    /**
     * Retrieves a period by its name.
     *
     * @param name The name of the period to retrieve.
     * @return A {@link Mono} emitting the found {@link Period}, or an empty Mono if not found.
     */
    Mono<Period> getPeriodByName(String name);
}
