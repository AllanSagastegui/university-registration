package pe.ask.university.api.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.handler.period.GetPeriodByIdHandler;
import pe.ask.university.api.handler.period.GetPeriodByNameHandler;
import pe.ask.university.api.handler.period.SavePeriodHandler;
import pe.ask.university.api.utils.routes.PeriodRoutes;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Defines the router functions for the period-related endpoints.
 * <p>
 * This class configures the routes for handling period operations, mapping them
 * to the appropriate handler functions. It uses Spring's functional web framework
 * to define the routing logic.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Configuration
public class PeriodRouterRest {

    /**
     * Creates a {@link RouterFunction} that defines the routes for period management.
     * <p>
     * This method configures the following routes:
     * <ul>
     *     <li>{@code GET /api/v1/period/{id}}: Retrieves a period by its ID.</li>
     *     <li>{@code GET /api/v1/period/name/{name}}: Retrieves a period by its name.</li>
     *     <li>{@code POST /api/v1/period}: Creates a new period.</li>
     * </ul>
     * </p>
     *
     * @param getPeriodByIdHandler   The handler for retrieving a period by its ID.
     * @param getPeriodByNameHandler The handler for retrieving a period by its name.
     * @param savePeriodHandler      The handler for creating a new period.
     * @return A {@link RouterFunction} that maps the routes to the corresponding handlers.
     */
    @Bean
    public RouterFunction<ServerResponse> periodRouterFunction(
            GetPeriodByIdHandler getPeriodByIdHandler,
            GetPeriodByNameHandler getPeriodByNameHandler,
            SavePeriodHandler savePeriodHandler
    ) {
        return route(GET(PeriodRoutes.GET_PERIOD_BY_ID).and(accept(MediaType.APPLICATION_JSON)), getPeriodByIdHandler::listenGETPeriodByIdUseCase)
                .andRoute(GET(PeriodRoutes.GET_PERIOD_BY_NAME).and(accept(MediaType.APPLICATION_JSON)), getPeriodByNameHandler::listenGETPeriodByNameUseCase)
                .andRoute(POST(PeriodRoutes.SAVE_PERIOD).and(accept(MediaType.APPLICATION_JSON)), savePeriodHandler::listenPOSTSavePeriodUseCase);
    }
}
