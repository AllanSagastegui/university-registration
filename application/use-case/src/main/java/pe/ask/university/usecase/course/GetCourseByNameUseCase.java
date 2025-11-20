package pe.ask.university.usecase.course;

import pe.ask.university.model.course.Course;
import pe.ask.university.port.in.usecase.course.IGetCourseByNameUseCase;
import pe.ask.university.port.out.persistence.ICourseRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.CourseNotFoundException;
import reactor.core.publisher.Mono;

/**
 * Use case for retrieving a {@link Course} by its name.
 * <p>
 * This class implements the {@link IGetCourseByNameUseCase} interface and provides the
 * business logic for fetching a single course. It interacts with the
 * {@link ICourseRepository} to retrieve course data.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetCourseByNameUseCase implements IGetCourseByNameUseCase {

    private final ICourseRepository repository;

    /**
     * Constructs a new {@code GetCourseByNameUseCase}.
     *
     * @param repository The repository for course persistence operations.
     */
    public GetCourseByNameUseCase(ICourseRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a course by its name.
     * <p>
     * This method fetches a course from the {@link ICourseRepository} using the
     * provided name. If no course is found with the given name, it emits a
     * {@link CourseNotFoundException}.
     * </p>
     *
     * @param name The name of the course to retrieve.
     * @return A {@link Mono} emitting the found {@link Course}.
     */
    @Override
    public Mono<Course> getCourseByName(String name) {
        return repository.getCourseByName(name)
                .switchIfEmpty(Mono.error(CourseNotFoundException::new));
    }
}
