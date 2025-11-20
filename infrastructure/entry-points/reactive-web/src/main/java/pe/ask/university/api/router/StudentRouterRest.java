package pe.ask.university.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.handler.student.*;
import pe.ask.university.api.utils.routes.StudentRoutes;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Defines the router functions for the student-related endpoints.
 * <p>
 * This class configures the routes for handling student operations, mapping them
 * to the appropriate handler functions. It uses Spring's functional web framework
 * to define the routing logic.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Configuration
public class StudentRouterRest {

    /**
     * Creates a {@link RouterFunction} that defines the routes for student management.
     * <p>
     * This method configures the following routes:
     * <ul>
     *     <li>{@code GET /api/v1/student}: Retrieves all students with pagination.</li>
     *     <li>{@code GET /api/v1/student/dni/{dni}}: Retrieves a student by their DNI.</li>
     *     <li>{@code GET /api/v1/student/email/{email}}: Retrieves a student by their email.</li>
     *     <li>{@code GET /api/v1/student/{id}}: Retrieves a student by their ID.</li>
     *     <li>{@code GET /api/v1/student/name/{name}}: Retrieves a student by their name.</li>
     *     <li>{@code POST /api/v1/student}: Creates a new student.</li>
     *     <li>{@code PUT /api/v1/student/{id}}: Updates an existing student.</li>
     * </ul>
     * </p>
     *
     * @param getAllStudentsHandler   The handler for retrieving all students.
     * @param getStudentByDniHandler  The handler for retrieving a student by their DNI.
     * @param getStudentByEmailHandler The handler for retrieving a student by their email.
     * @param getStudentByIdHandler   The handler for retrieving a student by their ID.
     * @param getStudentByNameHandler The handler for retrieving a student by their name.
     * @param saveStudentHandler      The handler for creating a new student.
     * @param updateStudentHandler    The handler for updating an existing student.
     * @return A {@link RouterFunction} that maps the routes to the corresponding handlers.
     */
    @Bean
    public RouterFunction<ServerResponse> studentRouterFunction(
            GetAllStudentsHandler getAllStudentsHandler,
            GetStudentByDniHandler getStudentByDniHandler,
            GetStudentByEmailHandler getStudentByEmailHandler,
            GetStudentByIdHandler getStudentByIdHandler,
            GetStudentByNameHandler getStudentByNameHandler,
            SaveStudentHandler saveStudentHandler,
            UpdateStudentHandler updateStudentHandler
    ) {
        return route(GET(StudentRoutes.GET_ALL_STUDENTS).and(accept(MediaType.APPLICATION_JSON)), getAllStudentsHandler::listenGETAllStudentsUseCase)
                .andRoute(GET(StudentRoutes.GET_STUDENT_BY_DNI).and(accept(MediaType.APPLICATION_JSON)), getStudentByDniHandler::listenGETStudentByDniUseCase)
                .andRoute(GET(StudentRoutes.GET_STUDENT_BY_EMAIL).and(accept(MediaType.APPLICATION_JSON)), getStudentByEmailHandler::listenGETStudentByEmailUseCase)
                .andRoute(GET(StudentRoutes.GET_STUDENT_BY_ID).and(accept(MediaType.APPLICATION_JSON)), getStudentByIdHandler::listenGETStudentByIdUseCase)
                .andRoute(GET(StudentRoutes.GET_STUDENT_BY_NAME).and(accept(MediaType.APPLICATION_JSON)), getStudentByNameHandler::listenGETStudentByNameUseCase)
                .andRoute(POST(StudentRoutes.SAVE_STUDENT).and(accept(MediaType.APPLICATION_JSON)), saveStudentHandler::listenPOSTSaveStudentUseCase)
                .andRoute(PUT(StudentRoutes.UPDATE_STUDENT).and(accept(MediaType.APPLICATION_JSON)), updateStudentHandler::listenPUTUpdateStudentUseCase);
    }
}
