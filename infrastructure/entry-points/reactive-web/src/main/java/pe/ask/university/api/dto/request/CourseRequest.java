package pe.ask.university.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request body for creating a new course")
public record CourseRequest(
        @Schema(description = "Name of the course", example = "Mathematics")
        @NotBlank(message = "Name cannot be blank")
        String name,

        @Schema(description = "Unique code of the course", example = "MATH-101")
        @NotBlank(message = "Code cannot be blank")
        String code,

        @Schema(description = "Description of the course", example = "An introductory course to mathematics")
        String description
) {
}
