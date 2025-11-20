package pe.ask.university.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.handler.enrollment.GetAllEnrollmentsHandler;
import pe.ask.university.api.handler.enrollment.GetEnrollmentByIdHandler;
import pe.ask.university.api.handler.enrollment.GetEnrollmentByStudentIdHandler;
import pe.ask.university.api.handler.enrollment.SaveEnrollmentHandler;
import pe.ask.university.api.utils.routes.EnrollmentRoutes;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Defines the router functions for the enrollment-related endpoints.
 * <p>
 * This class configures the routes for handling enrollment operations, mapping them
 * to the appropriate handler functions. It uses Spring's functional web framework
 * to define the routing logic.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Configuration
public class EnrollmentRouterRest {

    /**
     * Creates a {@link RouterFunction} that defines the routes for enrollment management.
     * <p>
     * This method configures the following routes:
     * <ul>
     *     <li>{@code GET /api/v1/enrollment}: Retrieves all enrollments with pagination.</li>
     *     <li>{@code GET /api/v1/enrollment/{id}}: Retrieves an enrollment by its ID.</li>
     *     <li>{@code GET /api/v1/enrollment/student/{studentId}}: Retrieves an enrollment by its student ID.</li>
     *     <li>{@code POST /api/v1/enrollment}: Creates a new enrollment.</li>
     * </ul>
     * </p>
     *
     * @param getAllEnrollmentsHandler      The handler for retrieving all enrollments.
     * @param getEnrollmentByIdHandler      The handler for retrieving an enrollment by its ID.
     * @param getEnrollmentByStudentIdHandler The handler for retrieving an enrollment by its student ID.
     * @param saveEnrollmentHandler         The handler for creating a new enrollment.
     * @return A {@link RouterFunction} that maps the routes to the corresponding handlers.
     */
    @Bean
    public RouterFunction<ServerResponse> enrollmentRouterFunction(
            GetAllEnrollmentsHandler getAllEnrollmentsHandler,
            GetEnrollmentByIdHandler getEnrollmentByIdHandler,
            GetEnrollmentByStudentIdHandler getEnrollmentByStudentIdHandler,
            SaveEnrollmentHandler saveEnrollmentHandler
    ){
        return route(GET(EnrollmentRoutes.GET_ALL_ENROLLMENTS).and(accept(MediaType.APPLICATION_JSON)), getAllEnrollmentsHandler::listenGETAllEnrollmentsUseCase)
                .andRoute(GET(EnrollmentRoutes.GET_ENROLLMENT_BY_ID).and(accept(MediaType.APPLICATION_JSON)), getEnrollmentByIdHandler::listenGETEnrollmentByIdUseCase)
                .andRoute(GET(EnrollmentRoutes.GET_ENROLLMENT_BY_STUDENT_ID).and(accept(MediaType.APPLICATION_JSON)), getEnrollmentByStudentIdHandler::listenGETEnrollmentByStudentIdUseCase)
                .andRoute(POST(EnrollmentRoutes.SAVE_ENROLLMENT).and(accept(MediaType.APPLICATION_JSON)), saveEnrollmentHandler::listenPOSTSaveEnrollmentUseCase);
    }
}
