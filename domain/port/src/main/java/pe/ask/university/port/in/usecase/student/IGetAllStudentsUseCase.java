package pe.ask.university.port.in.usecase.student;

import pe.ask.university.model.student.Student;
import pe.ask.university.model.utils.Pageable;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for retrieving all students with pagination.
 * <p>
 * This functional interface represents a use case for fetching a paginated list of
 * {@link Student} entities. Implementations will handle the logic for querying
 * and returning the requested page of students.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IGetAllStudentsUseCase {

    /**
     * Retrieves a paginated list of students.
     * <p>
     * This method takes pagination parameters (page and size) and returns a {@link Mono}
     * that emits a {@link Pageable} object containing the list of students for the
     * requested page and pagination metadata.
     * </p>
     *
     * @param page The page number to retrieve (0-indexed).
     * @param size The number of students per page.
     * @return A {@link Mono} emitting a {@link Pageable} containing the students.
     */
    Mono<Pageable<Student>> getAllStudents(int page, int size);
}
