package pe.ask.university.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(description = "Request body for creating a new enrollment")
public record EnrollmentRequest(
        @Schema(description = "ID of the student", example = "123e4567-e89b-12d3-a456-426614174000")
        @NotNull(message = "Student ID cannot be null")
        UUID studentId,

        @Schema(description = "ID of the course", example = "123e4567-e89b-12d3-a456-426614174001")
        @NotNull(message = "Course ID cannot be null")
        UUID courseId,

        @Schema(description = "ID of the period", example = "123e4567-e89b-12d3-a456-426614174002")
        @NotNull(message = "Period ID cannot be null")
        UUID periodId
) {
}
