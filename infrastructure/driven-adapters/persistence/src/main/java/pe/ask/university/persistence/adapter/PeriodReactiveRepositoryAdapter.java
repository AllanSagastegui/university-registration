package pe.ask.university.persistence.adapter;

import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import pe.ask.university.model.period.Period;
import pe.ask.university.persistence.entity.PeriodEntity;
import pe.ask.university.persistence.helper.ReactiveAdapterOperations;
import pe.ask.university.persistence.repository.IPeriodReactiveRepository;
import pe.ask.university.port.out.persistence.IPeriodRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Adapter class that implements the {@link IPeriodRepository} port, providing a concrete
 * implementation for period data persistence using a reactive repository.
 * <p>
 * This class acts as a bridge between the application's domain layer and the persistence
 * layer, handling the conversion between {@link Period} domain models and {@link PeriodEntity}
 * persistence entities. It leverages the {@link ReactiveAdapterOperations} for common
 * CRUD operations.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Repository
public class PeriodReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Period,
        PeriodEntity,
        UUID,
        IPeriodReactiveRepository
        > implements IPeriodRepository {

    /**
     * Constructs a new {@code PeriodReactiveRepositoryAdapter}.
     *
     * @param repository the reactive repository for period entities.
     * @param mapper     the object mapper for converting between domain and entity objects.
     */
    public PeriodReactiveRepositoryAdapter(IPeriodReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Period.class));
    }

    /**
     * Saves a new period to the database.
     *
     * @param period the period to save.
     * @return a {@link Mono} emitting the saved period.
     */
    @Override
    public Mono<Period> savePeriod(Period period) {
        return super.repository.save(toData(period))
                .map(this::toEntity);
    }

    /**
     * Retrieves a period by its name.
     *
     * @param name the name of the period to retrieve.
     * @return a {@link Mono} emitting the found period, or empty if not found.
     */
    @Override
    public Mono<Period> getPeriodByName(String name) {
        return super.repository.findByName(name)
                .map(this::toEntity);
    }

    /**
     * Retrieves a period by its ID.
     *
     * @param id the ID of the period to retrieve.
     * @return a {@link Mono} emitting the found period, or empty if not found.
     */
    @Override
    public Mono<Period> getPeriodById(UUID id) {
        return super.repository.findById(id)
                .map(this::toEntity);
    }
}
