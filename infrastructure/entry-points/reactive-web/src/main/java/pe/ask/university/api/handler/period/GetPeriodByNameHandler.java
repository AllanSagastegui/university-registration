package pe.ask.university.api.handler.period;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.mapper.IPeriodMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.period.IGetPeriodByNameUseCase;
import reactor.core.publisher.Mono;

/**
 * Handles the HTTP GET request for retrieving a period by its name.
 * <p>
 * This handler is responsible for processing requests to fetch a single period
 * based on its name. It extracts the name from the request path, invokes the
 * appropriate use case, maps the result to a response DTO, and constructs the
 * server response.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class GetPeriodByNameHandler {

    private final IGetPeriodByNameUseCase useCase;
    private final IPeriodMapper mapper;

    /**
     * Listens for and processes the GET request to retrieve a period by its name.
     * <p>
     * This method orchestrates the flow for fetching a period:
     * <ol>
     *     <li>Extracts the period name from the request's path variable.</li>
     *     <li>Invokes the {@link IGetPeriodByNameUseCase} to get the period.</li>
     *     <li>Maps the resulting domain model to a response DTO using {@link IPeriodMapper}.</li>
     *     <li>Builds a {@link ServerResponse} with status 200 (OK) and the response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the period name in the path.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenGETPeriodByNameUseCase(ServerRequest serverRequest) {
        String name = serverRequest.pathVariable("name");
        return useCase.getPeriodByName(name)
                .map(mapper::toResponse)
                .flatMap(response ->
                        ServerResponse
                                .ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response)
                )
                .onErrorResume(ex ->
                        Mono.error(ex instanceof BaseException ? ex : new UnexpectedException(ex))
                );
    }
}
