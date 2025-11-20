package pe.ask.university.persistence.repository;

import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.ask.university.persistence.entity.PeriodEntity;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IPeriodReactiveRepository extends ReactiveCrudRepository<PeriodEntity, UUID>, ReactiveQueryByExampleExecutor<PeriodEntity> {
    Mono<PeriodEntity> findByName(String name);
}
