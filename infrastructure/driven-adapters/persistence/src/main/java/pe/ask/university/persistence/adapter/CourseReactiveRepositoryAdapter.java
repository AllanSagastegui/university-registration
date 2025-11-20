package pe.ask.university.persistence.adapter;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import pe.ask.university.model.course.Course;
import pe.ask.university.persistence.entity.CourseEntity;
import pe.ask.university.persistence.helper.ReactiveAdapterOperations;
import pe.ask.university.persistence.repository.ICourseReactiveRepository;
import pe.ask.university.port.out.persistence.ICourseRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Adapter class that implements the {@link ICourseRepository} port, providing a concrete
 * implementation for course data persistence using a reactive repository.
 * <p>
 * This class acts as a bridge between the application's domain layer and the persistence
 * layer, handling the conversion between {@link Course} domain models and {@link CourseEntity}
 * persistence entities. It leverages the {@link ReactiveAdapterOperations} for common
 * CRUD operations.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Repository
public class CourseReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Course,
        CourseEntity,
        UUID,
        ICourseReactiveRepository
        > implements ICourseRepository {

    /**
     * Constructs a new {@code CourseReactiveRepositoryAdapter}.
     *
     * @param repository the reactive repository for course entities.
     * @param mapper     the object mapper for converting between domain and entity objects.
     */
    public CourseReactiveRepositoryAdapter(ICourseReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Course.class));
    }

    /**
     * Saves a new course to the database.
     *
     * @param course the course to save.
     * @return a {@link Mono} emitting the saved course.
     */
    @Override
    public Mono<Course> saveCourse(Course course) {
        return super.repository.save(toData(course))
                .map(this::toEntity);
    }

    /**
     * Retrieves a paginated list of all courses.
     *
     * @param page the page number to retrieve.
     * @param size the number of courses per page.
     * @return a {@link Flux} emitting the courses for the specified page.
     */
    @Override
    public Flux<Course> getAllCourses(int page, int size) {
        return super.repository.findAllPaginated(page * size, size)
                .map(this::toEntity);
    }

    /**
     * Retrieves a course by its name.
     *
     * @param name the name of the course to retrieve.
     * @return a {@link Mono} emitting the found course, or empty if not found.
     */
    @Override
    public Mono<Course> getCourseByName(String name) {
        return super.repository.findByName(name)
                .map(this::toEntity);
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param id the ID of the course to retrieve.
     * @return a {@link Mono} emitting the found course, or empty if not found.
     */
    @Override
    public Mono<Course> getCourseById(UUID id) {
        return super.repository.findById(id)
                .map(this::toEntity);
    }

    /**
     * Counts the total number of courses.
     *
     * @return a {@link Mono} emitting the total count of courses.
     */
    @Override
    public Mono<Long> countAll() {
        return super.repository.count();
    }
}
