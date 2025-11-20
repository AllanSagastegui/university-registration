package pe.ask.university.port.in.usecase.course;

import pe.ask.university.model.course.Course;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for saving a course.
 * <p>
 * This functional interface represents a use case for creating or updating a {@link Course}.
 * Implementations of this interface will handle the business logic required to persist
 * a course entity.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface ISaveCourseUseCase {

    /**
     * Saves a given course.
     * <p>
     * This method takes a {@link Course} object and returns a {@link Mono} that
     * emits the saved course upon successful completion.
     * </p>
     *
     * @param course The course to be saved.
     * @return A {@link Mono} emitting the saved {@link Course}.
     */
    Mono<Course> saveCourse(Course course);
}
