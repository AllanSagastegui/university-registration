package pe.ask.university.usecase.enrollment;

import pe.ask.university.model.enrollment.Enrollment;
import pe.ask.university.model.utils.Pageable;
import pe.ask.university.port.in.usecase.enrollment.IGetAllEnrollmentsUseCase;
import pe.ask.university.port.out.persistence.IEnrollmentRepository;
import pe.ask.university.usecase.utils.UseCase;
import reactor.core.publisher.Mono;

/**
 * Use case for retrieving all enrollments with pagination.
 * <p>
 * This class implements the {@link IGetAllEnrollmentsUseCase} interface and provides the
 * business logic for fetching a paginated list of enrollments. It interacts with the
 * {@link IEnrollmentRepository} to retrieve enrollment data and calculates pagination metadata.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@UseCase
public class GetAllEnrollmentsUseCase implements IGetAllEnrollmentsUseCase {

    private final IEnrollmentRepository repository;

    /**
     * Constructs a new {@code GetAllEnrollmentsUseCase}.
     *
     * @param repository The repository for enrollment persistence operations.
     */
    public GetAllEnrollmentsUseCase(IEnrollmentRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a paginated list of enrollments.
     * <p>
     * This method fetches enrollments from the {@link IEnrollmentRepository} based on the
     * provided page and size parameters. It then combines the fetched list of enrollments
     * with the total count of enrollments to construct a {@link Pageable} object,
     * which includes pagination metadata such as total pages and total elements.
     * </p>
     *
     * @param page The page number to retrieve (0-indexed).
     * @param size The number of enrollments per page.
     * @return A {@link Mono} emitting a {@link Pageable} object containing the
     *         list of enrollments and pagination information.
     */
    @Override
    public Mono<Pageable<Enrollment>> getAllEnrollments(int page, int size) {
        return repository.getAllEnrollments(page, size)
                .collectList()
                .zipWith(repository.countAll())
                .map(tuple -> Pageable.<Enrollment>builder()
                        .size(size)
                        .page(page)
                        .content(tuple.getT1())
                        .totalElements(tuple.getT2())
                        .totalPages((int) Math.ceil((double) tuple.getT2() / size))
                        .build()
                );
    }
}
