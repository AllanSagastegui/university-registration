package pe.ask.university.port.in.usecase.student;

import pe.ask.university.model.student.Student;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for retrieving a student by their DNI.
 * <p>
 * This functional interface represents a use case for fetching a single {@link Student}
 * based on their DNI. Implementations will handle the logic for querying and
 * returning the requested student.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetStudentByDniUseCase {

    /**
     * Retrieves a student by their DNI.
     *
     * @param dni The DNI of the student to retrieve.
     * @return A {@link Mono} emitting the found {@link Student}, or an empty Mono if not found.
     */
    Mono<Student> getStudentByDni(String dni);
}
