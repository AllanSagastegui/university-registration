package pe.ask.university.usecase.student;

import pe.ask.university.model.student.Student;
import pe.ask.university.port.in.usecase.student.ISaveStudentUseCase;
import pe.ask.university.port.out.persistence.IStudentRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.StudentAlreadyExistsException;
import reactor.core.publisher.Mono;

/**
 * Use case for saving a {@link Student}.
 * <p>
 * This class implements the {@link ISaveStudentUseCase} interface and provides the
 * business logic for creating or updating a student. It interacts with the
 * {@link IStudentRepository} to persist student data and includes validation
 * to ensure uniqueness of student email and DNI.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class SaveStudentUseCase implements ISaveStudentUseCase {

    private final IStudentRepository repository;

    /**
     * Constructs a new {@code SaveStudentUseCase}.
     *
     * @param repository The repository for student persistence operations.
     */
    public SaveStudentUseCase(IStudentRepository repository) {
        this.repository = repository;
    }

    /**
     * Saves a given student after validating its unique fields.
     * <p>
     * This method first validates that the student's email and DNI are unique.
     * If either the email or DNI already exists, it emits a {@link RuntimeException}.
     * Otherwise, it proceeds to save the student using the {@link IStudentRepository}.
     * </p>
     *
     * @param student The {@link Student} object to be saved.
     * @return A {@link Mono} emitting the saved {@link Student}.
     */
    @Override
    public Mono<Student> saveStudent(Student student) {
        return validateUniqueFields(student)
                .then(repository.saveStudent(student));
    }

    /**
     * Validates that the student's unique fields (email and DNI) do not already exist.
     * <p>
     * This private method checks if a student with the same email or DNI already exists
     * in the repository. If a duplicate is found for either, it emits a {@link StudentAlreadyExistsException}.
     * </p>
     *
     * @param student The {@link Student} object to validate.
     * @return A {@link Mono<Void>} that completes successfully if the fields are unique,
     *         or emits a {@link StudentAlreadyExistsException} if a duplicate is found.
     */
    private Mono<Void> validateUniqueFields(Student student) {
        return repository.getStudentByEmail(student.getEmail())
                .flatMap(found -> Mono.error(StudentAlreadyExistsException::new))
                .switchIfEmpty(Mono.defer(() ->
                        repository.getStudentByDni(student.getDni())
                                .flatMap(found -> Mono.error(StudentAlreadyExistsException::new))
                ))
                .then();
    }
}
