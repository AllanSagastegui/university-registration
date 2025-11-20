package pe.ask.university.port.in.usecase.course;

import pe.ask.university.model.course.Course;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Defines the input port for retrieving a course by its ID.
 * <p>
 * This functional interface represents a use case for fetching a single {@link Course}
 * based on its unique identifier. Implementations will handle the logic for
 * querying and returning the requested course.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetCourseByIdUseCase {

    /**
     * Retrieves a course by its unique identifier.
     *
     * @param id The UUID of the course to retrieve.
     * @return A {@link Mono} emitting the found {@link Course}, or an empty Mono if not found.
     */
    Mono<Course> getCourseById(UUID id);
}
