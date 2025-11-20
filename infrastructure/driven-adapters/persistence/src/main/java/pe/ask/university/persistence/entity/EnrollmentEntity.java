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
@Table("enrollment")
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentEntity {
    @Id
    @Column("id")
    private UUID id;

    @Column("student_id")
    private UUID studentId;

    @Column("course_id")
    private UUID courseId;

    @Column("period_id")
    private UUID periodId;

    @Column("created_at")
    private LocalDateTime createdAt;
}
