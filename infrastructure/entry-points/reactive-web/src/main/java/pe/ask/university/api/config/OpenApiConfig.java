package pe.ask.university.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "University Registration API",
                version = "1.0",
                description = "API for managing university registrations, including students, courses, and enrollments.",
                contact = @Contact(
                        name = "Allan Sagastegui",
                        email = "allansagasteguih@gmail.com",
                        url = ""
                )
        )
)
public class OpenApiConfig {
}