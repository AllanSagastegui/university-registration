package pe.ask.university.port.in.usecase.student;

import pe.ask.university.model.student.Student;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for retrieving a student by their name.
 * <p>
 * This functional interface represents a use case for fetching a single {@link Student}
 * based on their name. Implementations will handle the logic for querying and
 * returning the requested student.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetStudentByNameUseCase {

    /**
     * Retrieves a student by their name.
     *
     * @param name The name of the student to retrieve.
     * @return A {@link Mono} emitting the found {@link Student}, or an empty Mono if not found.
     */
    Mono<Student> getStudentByName(String name);
}
