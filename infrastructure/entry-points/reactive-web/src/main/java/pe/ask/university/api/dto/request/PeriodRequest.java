package pe.ask.university.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request body for creating a new period")
public record PeriodRequest(
        @Schema(description = "Name of the period", example = "2023-1")
        @NotBlank(message = "Name cannot be blank")
        String name
) {
}
