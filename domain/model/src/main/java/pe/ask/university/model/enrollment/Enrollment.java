package pe.ask.university.model.enrollment;

import java.time.LocalDateTime;
import java.util.UUID;

public class Enrollment {
    private UUID id;
    private UUID studentId;
    private UUID courseId;
    private UUID periodId;
    private LocalDateTime createdAt;

    public static EnrollmentBuilder builder() {
        return new EnrollmentBuilder();
    }

    public EnrollmentBuilder toBuilder() {
        return new EnrollmentBuilder().id(this.id).studentId(this.studentId).courseId(this.courseId).periodId(this.periodId).createdAt(this.createdAt);
    }

    public Enrollment() {
    }

    public Enrollment(UUID id, UUID studentId, UUID courseId, UUID periodId, LocalDateTime createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.periodId = periodId;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getCourseId() {
        return courseId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }

    public UUID getPeriodId() {
        return periodId;
    }

    public void setPeriodId(UUID periodId) {
        this.periodId = periodId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
