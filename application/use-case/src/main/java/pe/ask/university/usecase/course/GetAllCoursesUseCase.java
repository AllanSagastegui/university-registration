package pe.ask.university.usecase.course;

import pe.ask.university.model.course.Course;
import pe.ask.university.model.utils.Pageable;
import pe.ask.university.port.in.usecase.course.IGetAllCoursesUseCase;
import pe.ask.university.port.out.persistence.ICourseRepository;
import pe.ask.university.usecase.utils.UseCase;
import reactor.core.publisher.Mono;

/**
 * Use case for retrieving all courses with pagination.
 * <p>
 * This class implements the {@link IGetAllCoursesUseCase} interface and provides the
 * business logic for fetching a paginated list of courses. It interacts with the
 * {@link ICourseRepository} to retrieve course data and calculates pagination metadata.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetAllCoursesUseCase implements IGetAllCoursesUseCase {

    private final ICourseRepository repository;

    /**
     * Constructs a new {@code GetAllCoursesUseCase}.
     *
     * @param repository The repository for course persistence operations.
     */
    public GetAllCoursesUseCase(ICourseRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a paginated list of courses.
     * <p>
     * This method fetches courses from the {@link ICourseRepository} based on the
     * provided page and size parameters. It then combines the fetched list of courses
     * with the total count of courses to construct a {@link Pageable} object,
     * which includes pagination metadata such as total pages and total elements.
     * </p>
     *
     * @param page The page number to retrieve (0-indexed).
     * @param size The number of courses per page.
     * @return A {@link Mono} emitting a {@link Pageable} object containing the
     *         list of courses and pagination information.
     */
    @Override
    public Mono<Pageable<Course>> getAllCourses(int page, int size) {
        return repository.getAllCourses(page, size)
                .collectList()
                .zipWith(repository.countAll())
                .map(tuple -> Pageable.<Course>builder()
                        .size(size)
                        .page(page)
                        .content(tuple.getT1())
                        .totalElements(tuple.getT2())
                        .totalPages((int) Math.ceil((double) tuple.getT2() / size))
                        .build()
                );
    }
}
