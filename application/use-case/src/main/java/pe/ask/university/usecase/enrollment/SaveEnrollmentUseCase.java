package pe.ask.university.usecase.enrollment;

import pe.ask.university.model.enrollment.Enrollment;
import pe.ask.university.port.in.usecase.enrollment.ISaveEnrollmentUseCase;
import pe.ask.university.port.out.persistence.IEnrollmentRepository;
import pe.ask.university.usecase.utils.UseCase;
import reactor.core.publisher.Mono;

/**
 * Use case for saving an {@link Enrollment}.
 * <p>
 * This class implements the {@link ISaveEnrollmentUseCase} interface and provides the
 * business logic for creating or updating an enrollment. It interacts with the
 * {@link IEnrollmentRepository} to persist enrollment data.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class SaveEnrollmentUseCase implements ISaveEnrollmentUseCase {

    private final IEnrollmentRepository repository;

    /**
     * Constructs a new {@code SaveEnrollmentUseCase}.
     *
     * @param repository The repository for enrollment persistence operations.
     */
    public SaveEnrollmentUseCase(IEnrollmentRepository repository) {
        this.repository = repository;
    }

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
    @Override
    public Mono<Enrollment> saveEnrollment(Enrollment enrollment) {
        return repository.saveEnrollment(enrollment);
    }
}
