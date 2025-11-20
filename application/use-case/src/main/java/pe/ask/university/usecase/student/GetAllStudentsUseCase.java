package pe.ask.university.usecase.student;

import pe.ask.university.model.student.Student;
import pe.ask.university.model.utils.Pageable;
import pe.ask.university.port.in.usecase.student.IGetAllStudentsUseCase;
import pe.ask.university.port.out.persistence.IStudentRepository;
import pe.ask.university.usecase.utils.UseCase;
import reactor.core.publisher.Mono;

/**
 * Use case for retrieving all students with pagination.
 * <p>
 * This class implements the {@link IGetAllStudentsUseCase} interface and provides the
 * business logic for fetching a paginated list of students. It interacts with the
 * {@link IStudentRepository} to retrieve student data and calculates pagination metadata.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetAllStudentsUseCase implements IGetAllStudentsUseCase {

    private final IStudentRepository repository;

    /**
     * Constructs a new {@code GetAllStudentsUseCase}.
     *
     * @param repository The repository for student persistence operations.
     */
    public GetAllStudentsUseCase(IStudentRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a paginated list of students.
     * <p>
     * This method fetches students from the {@link IStudentRepository} based on the
     * provided page and size parameters. It then combines the fetched list of students
     * with the total count of students to construct a {@link Pageable} object,
     * which includes pagination metadata such as total pages and total elements.
     * </p>
     *
     * @param page The page number to retrieve (0-indexed).
     * @param size The number of students per page.
     * @return A {@link Mono} emitting a {@link Pageable} object containing the
     *         list of students and pagination information.
     */
    @Override
    public Mono<Pageable<Student>> getAllStudents(int page, int size) {
        return repository.getAllStudents(page, size)
                .collectList()
                .zipWith(repository.countAll())
                .map(tuple -> Pageable.<Student>builder()
                        .size(size)
                        .page(page)
                        .content(tuple.getT1())
                        .totalElements(tuple.getT2())
                        .totalPages((int) Math.ceil((double) tuple.getT2() / size))
                        .build()
                );
    }
}
