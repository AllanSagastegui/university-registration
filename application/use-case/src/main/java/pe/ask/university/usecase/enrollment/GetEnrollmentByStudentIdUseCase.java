package pe.ask.university.usecase.enrollment;

import pe.ask.university.model.enrollment.Enrollment;
import pe.ask.university.model.utils.Pageable;
import pe.ask.university.port.in.usecase.enrollment.IGetEnrollmentByStudentIdUseCase;
import pe.ask.university.port.out.persistence.IEnrollmentRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.EnrollmentNotFoundException;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Use case for retrieving enrollments by a student's ID.
 * <p>
 * This class implements the {@link IGetEnrollmentByStudentIdUseCase} interface and provides the
 * business logic for fetching all enrollments associated with a specific student.
 * It interacts with the {@link IEnrollmentRepository} to retrieve enrollment data.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetEnrollmentByStudentIdUseCase implements IGetEnrollmentByStudentIdUseCase {

    private final IEnrollmentRepository repository;

    /**
     * Constructs a new {@code GetEnrollmentByStudentIdUseCase}.
     *
     * @param repository The repository for enrollment persistence operations.
     */
    public GetEnrollmentByStudentIdUseCase(IEnrollmentRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all enrollments associated with a given student ID.
     * <p>
     * This method fetches all enrollments for a student from the {@link IEnrollmentRepository}
     * using the provided student ID. It then packages the results into a {@link Pageable} object.
     * </p>
     *
     * @param studentId The UUID of the student whose enrollments are to be retrieved.
     * @return A {@link Mono} emitting a {@link Pageable} object containing the list of
     *         enrollments and pagination information.
     */
    @Override
    public Mono<Pageable<Enrollment>> getEnrollmentByStudentId(UUID studentId) {
        return repository.getEnrollmentByStudentId(studentId)
                .collectList()
                .map(enrollments -> Pageable.<Enrollment>builder()
                        .size(enrollments.size())
                        .page(1)
                        .totalPages(1)
                        .totalElements(enrollments.size())
                        .content(enrollments)
                        .build()
                );
    }
}
