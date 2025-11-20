package pe.ask.university.port.in.usecase.enrollment;

import pe.ask.university.model.enrollment.Enrollment;
import pe.ask.university.model.utils.Pageable;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for retrieving all enrollments with pagination.
 * <p>
 * This functional interface represents a use case for fetching a paginated list of
 * {@link Enrollment} entities. Implementations will handle the logic for querying
 * and returning the requested page of enrollments.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetAllEnrollmentsUseCase {

    /**
     * Retrieves a paginated list of enrollments.
     * <p>
     * This method takes pagination parameters (page and size) and returns a {@link Mono}
     * that emits a {@link Pageable} object containing the list of enrollments for the
     * requested page and pagination metadata.
     * </p>
     *
     * @param page The page number to retrieve (0-indexed).
     * @param size The number of enrollments per page.
     * @return A {@link Mono} emitting a {@link Pageable} containing the enrollments.
     */
    Mono<Pageable<Enrollment>> getAllEnrollments(int page, int size);
}
