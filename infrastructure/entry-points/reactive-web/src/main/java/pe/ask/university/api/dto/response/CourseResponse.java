package pe.ask.university.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response body for a course")
public record CourseResponse(
        @Schema(description = "Name of the course", example = "Mathematics")
        String name,

        @Schema(description = "Unique code of the course", example = "MATH-101")
        String code,

        @Schema(description = "Description of the course", example = "An introductory course to mathematics")
        String description
) {
}
