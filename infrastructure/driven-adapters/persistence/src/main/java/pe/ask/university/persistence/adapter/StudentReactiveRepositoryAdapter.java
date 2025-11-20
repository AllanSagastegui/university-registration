package pe.ask.university.persistence.adapter;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import pe.ask.university.model.student.Student;
import pe.ask.university.persistence.entity.StudentEntity;
import pe.ask.university.persistence.helper.ReactiveAdapterOperations;
import pe.ask.university.persistence.repository.IStudentReactiveRepository;
import pe.ask.university.port.out.persistence.IStudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Adapter class that implements the {@link IStudentRepository} port, providing a concrete
 * implementation for student data persistence using a reactive repository.
 * <p>
 * This class acts as a bridge between the application's domain layer and the persistence
 * layer, handling the conversion between {@link Student} domain models and {@link StudentEntity}
 * persistence entities. It leverages the {@link ReactiveAdapterOperations} for common
 * CRUD operations.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Repository
public class StudentReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Student,
        StudentEntity,
        UUID,
        IStudentReactiveRepository
        > implements IStudentRepository {

    /**
     * Constructs a new {@code StudentReactiveRepositoryAdapter}.
     *
     * @param repository the reactive repository for student entities.
     * @param mapper     the object mapper for converting between domain and entity objects.
     */
    public StudentReactiveRepositoryAdapter(IStudentReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Student.class));
    }

    /**
     * Saves a new student to the database.
     *
     * @param student the student to save.
     * @return a {@link Mono} emitting the saved student.
     */
    @Override
    public Mono<Student> saveStudent(Student student) {
        return super.repository.save(toData(student))
                .map(this::toEntity);
    }

    /**
     * Retrieves a paginated list of all students.
     *
     * @param page the page number to retrieve.
     * @param size the number of students per page.
     * @return a {@link Flux} emitting the students for the specified page.
     */
    @Override
    public Flux<Student> getAllStudents(int page, int size) {
        return super.repository.findAllPaginated(page * size, size)
                .map(this::toEntity);
    }

    /**
     * Retrieves a student by their name.
     *
     * @param name the name of the student to retrieve.
     * @return a {@link Mono} emitting the found student, or empty if not found.
     */
    @Override
    public Mono<Student> getStudentByName(String name) {
        return super.repository.findByName(name)
                .map(this::toEntity);
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param id the ID of the student to retrieve.
     * @return a {@link Mono} emitting the found student, or empty if not found.
     */
    @Override
    public Mono<Student> getStudentById(UUID id) {
        return super.repository.findById(id)
                .map(this::toEntity);
    }

    /**
     * Retrieves a student by their email.
     *
     * @param email the email of the student to retrieve.
     * @return a {@link Mono} emitting the found student, or empty if not found.
     */
    @Override
    public Mono<Student> getStudentByEmail(String email) {
        return super.repository.findByEmail(email)
                .map(this::toEntity);
    }

    /**
     * Retrieves a student by their DNI.
     *
     * @param dni the DNI of the student to retrieve.
     * @return a {@link Mono} emitting the found student, or empty if not found.
     */
    @Override
    public Mono<Student> getStudentByDni(String dni) {
        return super.repository.findByDni(dni)
                .map(this::toEntity);
    }

    /**
     * Updates an existing student in the database.
     *
     * @param student the student with updated information to save.
     * @return a {@link Mono} emitting the updated student.
     */
    @Override
    public Mono<Student> updateStudent(UUID id, Student student) {
        return super.repository.update(
                student.getName(),
                student.getSurname(),
                student.getDni(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress(),
                student.getBirthdate(),
                id
        ).thenReturn(student);
    }

    /**
     * Counts the total number of students.
     *
     * @return a {@link Mono} emitting the total count of students.
     */
    @Override
    public Mono<Long> countAll() {
        return super.repository.count();
    }
}
