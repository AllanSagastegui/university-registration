package pe.ask.university.port.in.usecase.student;

import pe.ask.university.model.student.Student;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Defines the input port for updating an existing student.
 * <p>
 * This functional interface represents a use case for modifying an existing {@link Student}.
 * Implementations of this interface will handle the business logic required to update
 * a student entity.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface IUpdateStudentUseCase {

    /**
     * Updates an existing student.
     * <p>
     * This method takes a student ID and a {@link Student} object with updated information,
     * and returns a {@link Mono} that emits the updated student upon successful completion.
     * </p>
     *
     * @param id      The UUID of the student to be updated.
     * @param student The student object containing the updated information.
     * @return A {@link Mono} emitting the updated {@link Student}.
     */
    Mono<Student> updateStudent(UUID id, Student student);
}
