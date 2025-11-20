package pe.ask.university.port.in.usecase.enrollment;

import pe.ask.university.model.enrollment.Enrollment;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for saving an enrollment.
 * <p>
 * This functional interface represents a use case for creating or updating an {@link Enrollment}.
 * Implementations of this interface will handle the business logic required to persist
 * an enrollment entity.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface ISaveEnrollmentUseCase {

    /**
     * Saves a given enrollment.
     * <p>
     * This method takes an {@link Enrollment} object and returns a {@link Mono} that
     * emits the saved enrollment upon successful completion.
     * </p>
     *
     * @param enrollment The enrollment to be saved.
     * @return A {@link Mono} emitting the saved {@link Enrollment}.
     */
    Mono<Enrollment> saveEnrollment(Enrollment enrollment);
}
