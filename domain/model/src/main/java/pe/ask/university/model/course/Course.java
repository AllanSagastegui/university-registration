package pe.ask.university.model.course;

import java.time.LocalDateTime;
import java.util.UUID;

public class Course {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private LocalDateTime createdAt;

    public Course() {
    }

    public Course(UUID id, String name, String code, String description, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.createdAt = createdAt;
    }

    public static CourseBuilder builder() {
        return new CourseBuilder();
    }

    public CourseBuilder toBuilder() {
        return new CourseBuilder().id(this.id).name(this.name).code(this.code).description(this.description).createdAt(this.createdAt);
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
