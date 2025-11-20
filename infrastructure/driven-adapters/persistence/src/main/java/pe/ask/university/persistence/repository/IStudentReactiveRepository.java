package pe.ask.university.persistence.repository;

import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pe.ask.university.persistence.entity.StudentEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

public interface IStudentReactiveRepository extends ReactiveCrudRepository<StudentEntity, UUID>, ReactiveQueryByExampleExecutor<StudentEntity> {

    @Query("""
            SELECT * FROM student
            OFFSET :offset
            LIMIT :limit;
    """)
    Flux<StudentEntity> findAllPaginated(int offset, int limit);

    @Query("SELECT count(*) FROM student")
    Mono<Long> countAll();

    Mono<StudentEntity> findByEmail(String email);
    Mono<StudentEntity> findByName(String name);
    Mono<StudentEntity> findByDni(String dni);

    @Modifying
    @Query("""
        UPDATE student
        SET name = :name,
            surname = :surname,
            dni = :dni,
            email = :email,
            phone = :phone,
            address = :address,
            birthdate = :birthdate
        WHERE id = :id
    """)
    Mono<Integer> update(
            String name,
            String surname,
            String dni,
            String email,
            String phone,
            String address,
            LocalDate birthdate,
            UUID id
    );
}
