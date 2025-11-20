package pe.ask.university.model.enrollment;

import java.time.LocalDateTime;
import java.util.UUID;

public class EnrollmentBuilder {
    private UUID id;
    private UUID studentId;
    private UUID courseId;
    private UUID periodId;
    private LocalDateTime createdAt;

    EnrollmentBuilder() {
    }

    public EnrollmentBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public EnrollmentBuilder studentId(UUID studentId) {
        this.studentId = studentId;
        return this;
    }

    public EnrollmentBuilder courseId(UUID courseId) {
        this.courseId = courseId;
        return this;
    }

    public EnrollmentBuilder periodId(UUID periodId) {
        this.periodId = periodId;
        return this;
    }

    public EnrollmentBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Enrollment build() {
        return new Enrollment(id, studentId, courseId, periodId, createdAt);
    }
}
