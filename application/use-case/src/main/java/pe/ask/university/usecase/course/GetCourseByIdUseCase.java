package pe.ask.university.usecase.course;

import pe.ask.university.model.course.Course;
import pe.ask.university.port.in.usecase.course.IGetCourseByIdUseCase;
import pe.ask.university.port.out.persistence.ICourseRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.CourseNotFoundException;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Use case for retrieving a {@link Course} by its unique identifier.
 * <p>
 * This class implements the {@link IGetCourseByIdUseCase} interface and provides the
 * business logic for fetching a single course. It interacts with the
 * {@link ICourseRepository} to retrieve course data.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetCourseByIdUseCase implements IGetCourseByIdUseCase {

    private final ICourseRepository repository;

    /**
     * Constructs a new {@code GetCourseByIdUseCase}.
     *
     * @param repository The repository for course persistence operations.
     */
    public GetCourseByIdUseCase(ICourseRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a course by its unique identifier.
     * <p>
     * This method fetches a course from the {@link ICourseRepository} using the
     * provided ID. If no course is found with the given ID, it emits a
     * {@link CourseNotFoundException}.
     * </p>
     *
     * @param id The UUID of the course to retrieve.
     * @return A {@link Mono} emitting the found {@link Course}.
     */
    @Override
    public Mono<Course> getCourseById(UUID id) {
        return repository.getCourseById(id)
                .switchIfEmpty(Mono.error(CourseNotFoundException::new));
    }
}
