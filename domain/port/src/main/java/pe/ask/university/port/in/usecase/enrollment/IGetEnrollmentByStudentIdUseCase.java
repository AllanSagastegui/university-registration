package pe.ask.university.port.in.usecase.enrollment;

import pe.ask.university.model.enrollment.Enrollment;
import pe.ask.university.model.utils.Pageable;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Defines the input port for retrieving an enrollment by its student ID.
 * <p>
 * This functional interface represents a use case for fetching a single {@link Enrollment}
 * based on the ID of the student associated with it. Implementations will handle the logic
 * for querying and returning the requested enrollment.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetEnrollmentByStudentIdUseCase {

    /**
     * Retrieves an enrollment by its associated student ID.
     *
     * @param studentId The UUID of the student whose enrollment is to be retrieved.
     * @return A {@link Mono} emitting the found {@link Enrollment}, or an empty Mono if not found.
     */
    Mono<Pageable<Enrollment>> getEnrollmentByStudentId(UUID studentId);
}
