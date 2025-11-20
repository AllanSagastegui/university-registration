package pe.ask.university.usecase.student;

import pe.ask.university.model.student.Student;
import pe.ask.university.port.in.usecase.student.IGetStudentByDniUseCase;
import pe.ask.university.port.out.persistence.IStudentRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.StudentNotFoundException;
import reactor.core.publisher.Mono;

/**
 * Use case for retrieving a {@link Student} by their DNI.
 * <p>
 * This class implements the {@link IGetStudentByDniUseCase} interface and provides the
 * business logic for fetching a single student. It interacts with the
 * {@link IStudentRepository} to retrieve student data.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetStudentByDniUseCase implements IGetStudentByDniUseCase {

    private final IStudentRepository repository;

    /**
     * Constructs a new {@code GetStudentByDniUseCase}.
     *
     * @param repository The repository for student persistence operations.
     */
    public GetStudentByDniUseCase(IStudentRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a student by their DNI.
     * <p>
     * This method fetches a student from the {@link IStudentRepository} using the
     * provided DNI. If no student is found with the given DNI, it emits a
     * {@link StudentNotFoundException}.
     * </p>
     *
     * @param dni The DNI of the student to retrieve.
     * @return A {@link Mono} emitting the found {@link Student}.
     */
    @Override
    public Mono<Student> getStudentByDni(String dni) {
        return repository.getStudentByDni(dni)
                .switchIfEmpty(Mono.error(StudentNotFoundException::new));
    }
}
