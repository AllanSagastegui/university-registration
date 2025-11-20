package pe.ask.university.port.in.usecase.period;

import pe.ask.university.model.period.Period;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for saving a period.
 * <p>
 * This functional interface represents a use case for creating or updating a {@link Period}.
 * Implementations of this interface will handle the business logic required to persist
 * a period entity.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface ISavePeriodUseCase {

    /**
     * Saves a given period.
     * <p>
     * This method takes a {@link Period} object and returns a {@link Mono} that
     * emits the saved period upon successful completion.
     * </p>
     *
     * @param period The period to be saved.
     * @return A {@link Mono} emitting the saved {@link Period}.
     */
    Mono<Period> savePeriod(Period period);
}
