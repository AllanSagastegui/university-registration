package pe.ask.university.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response body for a period")
public record PeriodResponse(
        @Schema(description = "Name of the period", example = "2023-1")
        String name
) {
}
