package pe.ask.university.persistence.adapter;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import pe.ask.university.model.enrollment.Enrollment;
import pe.ask.university.persistence.entity.EnrollmentEntity;
import pe.ask.university.persistence.helper.ReactiveAdapterOperations;
import pe.ask.university.persistence.repository.IEnrollmentReactiveRepository;
import pe.ask.university.port.out.persistence.IEnrollmentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Adapter class that implements the {@link IEnrollmentRepository} port, providing a concrete
 * implementation for enrollment data persistence using a reactive repository.
 * <p>
 * This class acts as a bridge between the application's domain layer and the persistence
 * layer, handling the conversion between {@link Enrollment} domain models and {@link EnrollmentEntity}
 * persistence entities. It leverages the {@link ReactiveAdapterOperations} for common
 * CRUD operations.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Repository
public class EnrollmentReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Enrollment,
        EnrollmentEntity,
        UUID,
        IEnrollmentReactiveRepository
        > implements IEnrollmentRepository {

    /**
     * Constructs a new {@code EnrollmentReactiveRepositoryAdapter}.
     *
     * @param repository the reactive repository for enrollment entities.
     * @param mapper     the object mapper for converting between domain and entity objects.
     */
    public EnrollmentReactiveRepositoryAdapter(IEnrollmentReactiveRepository repository, ObjectMapper mapper){
        super(repository, mapper, d -> mapper.map(d, Enrollment.class));
    }

    /**
     * Saves a new enrollment to the database.
     *
     * @param enrollment the enrollment to save.
     * @return a {@link Mono} emitting the saved enrollment.
     */
    @Override
    public Mono<Enrollment> saveEnrollment(Enrollment enrollment) {
        return super.repository.save(toData(enrollment))
                .map(this::toEntity);
    }

    /**
     * Retrieves a paginated list of all enrollments.
     *
     * @param page the page number to retrieve.
     * @param size the number of enrollments per page.
     * @return a {@link Flux} emitting the enrollments for the specified page.
     */
    @Override
    public Flux<Enrollment> getAllEnrollments(int page, int size) {
        return super.repository.findAllPaginated(page*size, size)
                .map(this::toEntity);
    }

    /**
     * Retrieves an enrollment by its ID.
     *
     * @param id the ID of the enrollment to retrieve.
     * @return a {@link Mono} emitting the found enrollment, or empty if not found.
     */
    @Override
    public Mono<Enrollment> getEnrollmentById(UUID id) {
        return super.repository.findById(id)
                .map(this::toEntity);
    }

    /**
     * Retrieves an enrollment by its student ID.
     *
     * @param studentId the student ID of the enrollment to retrieve.
     * @return a {@link Flux} emitting the found enrollment, or empty if not found.
     */
    @Override
    public Flux<Enrollment> getEnrollmentByStudentId(UUID studentId) {
        return super.repository.findByStudentId(studentId)
                .map(this::toEntity);
    }

    /**
     * Counts the total number of enrollments.
     *
     * @return a {@link Mono} emitting the total count of enrollments.
     */
    @Override
    public Mono<Long> countAll() {
        return super.repository.count();
    }
}
