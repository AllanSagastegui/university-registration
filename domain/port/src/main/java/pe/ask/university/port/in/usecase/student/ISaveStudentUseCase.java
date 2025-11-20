package pe.ask.university.port.in.usecase.student;

import pe.ask.university.model.student.Student;
import reactor.core.publisher.Mono;

/**
 * Defines the input port for saving a student.
 * <p>
 * This functional interface represents a use case for creating or updating a {@link Student}.
 * Implementations of this interface will handle the business logic required to persist
 * a student entity.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@FunctionalInterface
public interface ISaveStudentUseCase {

    /**
     * Saves a given student.
     * <p>
     * This method takes a {@link Student} object and returns a {@link Mono} that
     * emits the saved student upon successful completion.
     * </p>
     *
     * @param student The student to be saved.
     * @return A {@link Mono} emitting the saved {@link Student}.
     */
    Mono<Student> saveStudent(Student student);
}
