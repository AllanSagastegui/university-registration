package pe.ask.university.usecase.enrollment;

import pe.ask.university.model.enrollment.Enrollment;
import pe.ask.university.port.in.usecase.enrollment.IGetEnrollmentByIdUseCase;
import pe.ask.university.port.out.persistence.IEnrollmentRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.EnrollmentNotFoundException;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Use case for retrieving an {@link Enrollment} by its unique identifier.
 * <p>
 * This class implements the {@link IGetEnrollmentByIdUseCase} interface and provides the
 * business logic for fetching a single enrollment. It interacts with the
 * {@link IEnrollmentRepository} to retrieve enrollment data.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetEnrollmentByIdUseCase implements IGetEnrollmentByIdUseCase {

    private final IEnrollmentRepository repository;

    /**
     * Constructs a new {@code GetEnrollmentByIdUseCase}.
     *
     * @param repository The repository for enrollment persistence operations.
     */
    public GetEnrollmentByIdUseCase(IEnrollmentRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves an enrollment by its unique identifier.
     * <p>
     * This method fetches an enrollment from the {@link IEnrollmentRepository} using the
     * provided ID. If no enrollment is found with the given ID, it emits a
     * {@link EnrollmentNotFoundException}.
     * </p>
     *
     * @param id The UUID of the enrollment to retrieve.
     * @return A {@link Mono} emitting the found {@link Enrollment}.
     */
    @Override
    public Mono<Enrollment> getEnrollmentById(UUID id) {
        return repository.getEnrollmentById(id)
                .switchIfEmpty(Mono.error(EnrollmentNotFoundException::new));
    }
}
