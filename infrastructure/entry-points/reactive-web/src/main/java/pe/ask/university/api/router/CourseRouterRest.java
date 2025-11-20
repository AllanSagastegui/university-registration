package pe.ask.university.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.handler.course.GetAllCoursesHandler;
import pe.ask.university.api.handler.course.GetCourseByIdHandler;
import pe.ask.university.api.handler.course.GetCourseByNameHandler;
import pe.ask.university.api.handler.course.SaveCourseHandler;
import pe.ask.university.api.utils.routes.CourseRoutes;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Defines the router functions for the course-related endpoints.
 * <p>
 * This class configures the routes for handling course operations, mapping them
 * to the appropriate handler functions. It uses Spring's functional web framework
 * to define the routing logic.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Configuration
public class CourseRouterRest {

    /**
     * Creates a {@link RouterFunction} that defines the routes for course management.
     * <p>
     * This method configures the following routes:
     * <ul>
     *     <li>{@code GET /api/v1/course}: Retrieves all courses with pagination.</li>
     *     <li>{@code GET /api/v1/course/{id}}: Retrieves a course by its ID.</li>
     *     <li>{@code GET /api/v1/course/name/{name}}: Retrieves a course by its name.</li>
     *     <li>{@code POST /api/v1/course}: Creates a new course.</li>
     * </ul>
     * </p>
     *
     * @param getAllCoursesHandler   The handler for retrieving all courses.
     * @param getCourseByIdHandler   The handler for retrieving a course by its ID.
     * @param getCourseByNameHandler The handler for retrieving a course by its name.
     * @param saveCourseHandler      The handler for creating a new course.
     * @return A {@link RouterFunction} that maps the routes to the corresponding handlers.
     */
    @Bean
    public RouterFunction<ServerResponse> courseRouterFunction(
            GetAllCoursesHandler getAllCoursesHandler,
            GetCourseByIdHandler getCourseByIdHandler,
            GetCourseByNameHandler getCourseByNameHandler,
            SaveCourseHandler saveCourseHandler
    ){
        return route(GET(CourseRoutes.GET_ALL_COURSES).and(accept(MediaType.APPLICATION_JSON)), getAllCoursesHandler::listenGETAllCoursesUseCase)
                .andRoute(GET(CourseRoutes.GET_COURSE_BY_ID).and(accept(MediaType.APPLICATION_JSON)), getCourseByIdHandler::listenGETCourseByIdUseCase)
                .andRoute(GET(CourseRoutes.GET_COURSE_BY_NAME).and(accept(MediaType.APPLICATION_JSON)), getCourseByNameHandler::listenGETCourseByNameUseCase)
                .andRoute(POST(CourseRoutes.SAVE_COURSE).and(accept(MediaType.APPLICATION_JSON)), saveCourseHandler::listenPOSTSaveCourseUseCase);
    }
}
