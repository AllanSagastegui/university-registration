package pe.ask.university.usecase.student;

import pe.ask.university.model.student.Student;
import pe.ask.university.port.in.usecase.student.IGetStudentByIdUseCase;
import pe.ask.university.port.out.persistence.IStudentRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.StudentNotFoundException;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Use case for retrieving a {@link Student} by their unique identifier.
 * <p>
 * This class implements the {@link IGetStudentByIdUseCase} interface and provides the
 * business logic for fetching a single student. It interacts with the
 * {@link IStudentRepository} to retrieve student data.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetStudentByIdUseCase implements IGetStudentByIdUseCase {

    private final IStudentRepository repository;

    /**
     * Constructs a new {@code GetStudentByIdUseCase}.
     *
     * @param repository The repository for student persistence operations.
     */
    public GetStudentByIdUseCase(IStudentRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a student by their unique identifier.
     * <p>
     * This method fetches a student from the {@link IStudentRepository} using the
     * provided ID. If no student is found with the given ID, it emits a
     * {@link StudentNotFoundException}.
     * </p>
     *
     * @param id The UUID of the student to retrieve.
     * @return A {@link Mono} emitting the found {@link Student}.
     */
    @Override
    public Mono<Student> getStudentById(UUID id) {
        return repository.getStudentById(id)
                .switchIfEmpty(Mono.error(StudentNotFoundException::new));
    }
}
