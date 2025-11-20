package pe.ask.university.usecase.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to mark classes as Use Cases within the application.
 * <p>
 * This annotation serves as a stereotype for identifying and organizing the
 * application's use case implementations. It can be used by frameworks or
 * tools for component scanning, dependency injection, or documentation generation.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseCase {
}
