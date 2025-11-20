package pe.ask.university.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Table("course")
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {
    @Id
    @Column("id")
    private UUID id;

    @Column("name")
    private String name;

    @Column("code")
    private String code;

    @Column("description")
    private String description;

    @Column("created_at")
    private LocalDateTime createdAt;
}
