package pe.ask.university.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Response body for an enrollment")
public record EnrollmentResponse(
        @Schema(description = "ID of the student", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID studentId,

        @Schema(description = "ID of the course", example = "123e4567-e89b-12d3-a456-426614174001")
        UUID courseId,

        @Schema(description = "ID of the period", example = "123e4567-e89b-12d3-a456-426614174002")
        UUID periodId
) {
}
