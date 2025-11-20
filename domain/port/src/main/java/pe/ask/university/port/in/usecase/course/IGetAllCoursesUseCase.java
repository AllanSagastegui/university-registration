package pe.ask.university.port.in.usecase.course;

import pe.ask.university.model.course.Course;
import pe.ask.university.model.utils.Pageable;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for retrieving all courses with pagination.
 * <p>
 * This functional interface represents a use case for fetching a paginated list of
 * {@link Course} entities. Implementations will handle the logic for querying
 * and returning the requested page of courses.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetAllCoursesUseCase {

    /**
     * Retrieves a paginated list of courses.
     * <p>
     * This method takes pagination parameters (page and size) and returns a {@link Mono}
     * that emits a {@link Pageable} object containing the list of courses for the
     * requested page and pagination metadata.
     * </p>
     *
     * @param page The page number to retrieve (0-indexed).
     * @param size The number of courses per page.
     * @return A {@link Mono} emitting a {@link Pageable} containing the courses.
     */
    Mono<Pageable<Course>> getAllCourses(int page, int size);
}
