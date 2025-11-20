package pe.ask.university.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import pe.ask.university.usecase.utils.UseCase;

@Configuration
@ComponentScan(
        basePackages = {
                "pe.ask.university.usecase"
        },
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                value = UseCase.class
        ),
        useDefaultFilters = false
)
public class UseCaseConfig {
}
