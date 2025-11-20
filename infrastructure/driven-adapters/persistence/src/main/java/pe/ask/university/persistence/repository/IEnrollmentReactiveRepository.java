package pe.ask.university.persistence.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.ask.university.persistence.entity.EnrollmentEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface IEnrollmentReactiveRepository extends ReactiveCrudRepository<EnrollmentEntity, UUID>, ReactiveQueryByExampleExecutor<EnrollmentEntity> {
    @Query("""
            SELECT * FROM enrollment
            OFFSET :offset
            LIMIT :limit;
    """)
    Flux<EnrollmentEntity> findAllPaginated(int offset, int limit);

    Flux<EnrollmentEntity> findByStudentId(UUID studentId);

    @Query("SELECT count(*) FROM enrollment")
    Mono<Long> countAll();
}
