package pe.ask.university.usecase.student;

import pe.ask.university.model.student.Student;
import pe.ask.university.port.in.usecase.student.IUpdateStudentUseCase;
import pe.ask.university.port.out.persistence.IStudentRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.StudentAlreadyExistsException;
import pe.ask.university.usecase.utils.exception.StudentNotFoundException;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Use case for updating an existing {@link Student}.
 * <p>
 * This class implements the {@link IUpdateStudentUseCase} interface and provides the
 * business logic for modifying an existing student. It interacts with the
 * {@link IStudentRepository} to persist updated student data and includes validation
 * to ensure uniqueness of student email and DNI, excluding the current student.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class UpdateStudentUseCase implements IUpdateStudentUseCase {

    private final IStudentRepository repository;

    /**
     * Constructs a new {@code UpdateStudentUseCase}.
     *
     * @param repository The repository for student persistence operations.
     */
    public UpdateStudentUseCase(IStudentRepository repository) {
        this.repository = repository;
    }

    /**
     * Updates an existing student after validating its unique fields.
     * <p>
     * This method first retrieves the existing student by ID. If the student is not found,
     * it emits a {@link StudentNotFoundException}. It then validates that the updated student's
     * email and DNI are unique among other students. If a duplicate is found, it emits
     * a {@link StudentAlreadyExistsException}. Otherwise, it proceeds to update the student using
     * the {@link IStudentRepository}.
     * </p>
     *
     * @param id      The UUID of the student to be updated.
     * @param student The {@link Student} object containing the updated information.
     * @return A {@link Mono} emitting the updated {@link Student}.
     */
    @Override
    public Mono<Student> updateStudent(UUID id, Student student) {
        return repository.getStudentById(id)
                .switchIfEmpty(Mono.error(StudentNotFoundException::new))
                .flatMap(existing ->
                        validateUniqueFields(id, student)
                                .then(repository.updateStudent(id, student))
                );
    }

    /**
     * Validates that the student's unique fields (email and DNI) do not already exist
     * for other students.
     * <p>
     * This private method checks if a student with the same email or DNI already exists
     * in the repository, excluding the student being updated. If a duplicate is found
     * for either, it emits a {@link StudentAlreadyExistsException}.
     * </p>
     *
     * @param id      The UUID of the student being updated.
     * @param student The {@link Student} object with updated information to validate.
     * @return A {@link Mono<Void>} that completes successfully if the fields are unique,
     *         or emits a {@link StudentAlreadyExistsException} if a duplicate is found.
     */
    private Mono<Void> validateUniqueFields(UUID id, Student student) {
        return repository.getStudentByEmail(student.getEmail())
                .filter(s -> !s.getId().equals(id))
                .flatMap(s -> Mono.error(StudentAlreadyExistsException::new))
                .switchIfEmpty(Mono.defer(() ->
                        repository.getStudentByDni(student.getDni())
                                .filter(s -> !s.getId().equals(id))
                                .flatMap(s -> Mono.error(StudentAlreadyExistsException::new))
                ))
                .then();
    }
}
