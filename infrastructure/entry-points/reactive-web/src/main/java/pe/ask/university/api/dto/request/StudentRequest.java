package pe.ask.university.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Schema(description = "Request body for creating a new student")
public record StudentRequest(
        @Schema(description = "Name of the student", example = "John")
        @NotBlank(message = "Name cannot be blank")
        String name,

        @Schema(description = "Surname of the student", example = "Doe")
        @NotBlank(message = "Surname cannot be blank")
        String surname,

        @Schema(description = "DNI of the student", example = "12345678A")
        @NotBlank(message = "DNI cannot be blank")
        String dni,

        @Schema(description = "Email of the student", example = "john.doe@example.com")
        @NotBlank(message = "Email cannot be blank")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email should be valid")
        String email,

        @Schema(description = "Phone number of the student", example = "123456789")
        String phone,

        @Schema(description = "Address of the student", example = "123 Main St")
        String address,

        @Schema(description = "Birthdate of the student", example = "1990-01-01")
        @Past(message = "Birthdate should be in the past")
        Date birthdate
) {
}
