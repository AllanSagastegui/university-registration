package pe.ask.university.model.course;

import java.time.LocalDateTime;
import java.util.UUID;

public class CourseBuilder {
    private UUID id;
    private String name;
    private String code;
    private String description;
    private LocalDateTime createdAt;

    CourseBuilder() {
    }

    public CourseBuilder id(UUID id) {
        this.id = id;
        return this;
    }

    public CourseBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CourseBuilder code(String code) {
        this.code = code;
        return this;
    }

    public CourseBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CourseBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}
