package pe.ask.university.usecase.student;

import pe.ask.university.model.student.Student;
import pe.ask.university.port.in.usecase.student.IGetStudentByEmailUseCase;
import pe.ask.university.port.out.persistence.IStudentRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.StudentNotFoundException;
import reactor.core.publisher.Mono;

/**
 * Use case for retrieving a {@link Student} by their email address.
 * <p>
 * This class implements the {@link IGetStudentByEmailUseCase} interface and provides the
 * business logic for fetching a single student. It interacts with the
 * {@link IStudentRepository} to retrieve student data.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetStudentByEmailUseCase implements IGetStudentByEmailUseCase {

    private final IStudentRepository repository;

    /**
     * Constructs a new {@code GetStudentByEmailUseCase}.
     *
     * @param repository The repository for student persistence operations.
     */
    public GetStudentByEmailUseCase(IStudentRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a student by their email address.
     * <p>
     * This method fetches a student from the {@link IStudentRepository} using the
     * provided email. If no student is found with the given email, it emits a
     * {@link StudentNotFoundException}.
     * </p>
     *
     * @param email The email address of the student to retrieve.
     * @return A {@link Mono} emitting the found {@link Student}.
     */
    @Override
    public Mono<Student> getStudentByEmail(String email) {
        return repository.getStudentByEmail(email)
                .switchIfEmpty(Mono.error(StudentNotFoundException::new));
    }
}
