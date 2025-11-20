package pe.ask.university.persistence.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.ask.university.persistence.entity.CourseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ICourseReactiveRepository extends ReactiveCrudRepository<CourseEntity, UUID>, ReactiveQueryByExampleExecutor<CourseEntity> {
    @Query("""
            SELECT * FROM course
            OFFSET :offset
            LIMIT :limit;
    """)
    Flux<CourseEntity> findAllPaginated(int offset, int limit);

    Mono<CourseEntity> findByName(String name);

    @Query("SELECT count(*) FROM course")
    Mono<Long> countAll();
}
