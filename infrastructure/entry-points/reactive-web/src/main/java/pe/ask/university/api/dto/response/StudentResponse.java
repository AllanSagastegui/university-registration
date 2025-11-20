package pe.ask.university.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Response body for a student")
public record StudentResponse(
        @Schema(description = "Name of the student", example = "John")
        String name,

        @Schema(description = "Surname of the student", example = "Doe")
        String surname,

        @Schema(description = "DNI of the student", example = "12345678A")
        String dni,

        @Schema(description = "Email of the student", example = "john.doe@example.com")
        String email,

        @Schema(description = "Phone number of the student", example = "123456789")
        String phone,

        @Schema(description = "Address of the student", example = "123 Main St")
        String address,

        @Schema(description = "Birthdate of the student", example = "1990-01-01")
        LocalDate birthdate
) {
}
