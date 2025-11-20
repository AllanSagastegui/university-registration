package pe.ask.university.port.in.usecase.enrollment;

import pe.ask.university.model.enrollment.Enrollment;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Defines the input port for retrieving an enrollment by its ID.
 * <p>
 * This functional interface represents a use case for fetching a single {@link Enrollment}
 * based on its unique identifier. Implementations will handle the logic for
 * querying and returning the requested enrollment.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetEnrollmentByIdUseCase {

    /**
     * Retrieves an enrollment by its unique identifier.
     *
     * @param id The UUID of the enrollment to retrieve.
     * @return A {@link Mono} emitting the found {@link Enrollment}, or an empty Mono if not found.
     */
    Mono<Enrollment> getEnrollmentById(UUID id);
}
