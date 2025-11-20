package pe.ask.university.port.in.usecase.course;

import pe.ask.university.model.course.Course;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for retrieving a course by its name.
 * <p>
 * This functional interface represents a use case for fetching a single {@link Course}
 * based on its name. Implementations will handle the logic for querying and
 * returning the requested course.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetCourseByNameUseCase {

    /**
     * Retrieves a course by its name.
     *
     * @param name The name of the course to retrieve.
     * @return A {@link Mono} emitting the found {@link Course}, or an empty Mono if not found.
     */
    Mono<Course> getCourseByName(String name);
}
