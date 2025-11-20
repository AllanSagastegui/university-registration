package pe.ask.university.usecase.course;

import pe.ask.university.model.course.Course;
import pe.ask.university.port.in.usecase.course.ISaveCourseUseCase;
import pe.ask.university.port.out.persistence.ICourseRepository;
import pe.ask.university.usecase.utils.UseCase;
import pe.ask.university.usecase.utils.exception.CourseAlreadyExistsException;
import reactor.core.publisher.Mono;

/**
 * Use case for saving a {@link Course}.
 * <p>
 * This class implements the {@link ISaveCourseUseCase} interface and provides the
 * business logic for creating or updating a course. It interacts with the
 * {@link ICourseRepository} to persist course data and includes validation
 * to ensure uniqueness of course names.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class SaveCourseUseCase implements ISaveCourseUseCase {

    private final ICourseRepository repository;

    /**
     * Constructs a new {@code SaveCourseUseCase}.
     *
     * @param repository The repository for course persistence operations.
     */
    public SaveCourseUseCase(ICourseRepository repository) {
        this.repository = repository;
    }

    /**
     * Saves a given course after validating its unique fields.
     * <p>
     * This method first validates that the course name is unique. If the name
     * already exists, it emits a {@link RuntimeException}. Otherwise, it proceeds
     * to save the course using the {@link ICourseRepository}.
     * </p>
     *
     * @param course The {@link Course} object to be saved.
     * @return A {@link Mono} emitting the saved {@link Course}.
     */
    @Override
    public Mono<Course> saveCourse(Course course) {
        return validateUniqueFields(course)
                .then(repository.saveCourse(course));
    }

    /**
     * Validates that the course's unique fields (e.g., name) do not already exist.
     * <p>
     * This private method checks if a course with the same name already exists
     * in the repository. If a duplicate is found, it emits a {@link CourseAlreadyExistsException}.
     * </p>
     *
     * @param course The {@link Course} object to validate.
     * @return A {@link Mono<Void>} that completes successfully if the fields are unique,
     *         or emits a {@link CourseAlreadyExistsException} if a duplicate is found.
     */
    private Mono<Void> validateUniqueFields(Course course) {
        return repository.getCourseByName(course.getName())
                .flatMap(found -> Mono.<Void>error(CourseAlreadyExistsException::new))
                .then();
    }
}
