package pe.ask.university.api.handler.period;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.mapper.IPeriodMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.period.IGetPeriodByIdUseCase;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Handles the HTTP GET request for retrieving a period by its ID.
 * <p>
 * This handler is responsible for processing requests to fetch a single period
 * based on its unique identifier. It extracts the ID from the request path,
 * invokes the appropriate use case, maps the result to a response DTO, and
 * constructs the server response.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class GetPeriodByIdHandler {

    private final IGetPeriodByIdUseCase useCase;
    private final IPeriodMapper mapper;

    /**
     * Listens for and processes the GET request to retrieve a period by its ID.
     * <p>
     * This method orchestrates the flow for fetching a period:
     * <ol>
     *     <li>Extracts the period ID from the request's path variable.</li>
     *     <li>Invokes the {@link IGetPeriodByIdUseCase} to get the period.</li>
     *     <li>Maps the resulting domain model to a response DTO using {@link IPeriodMapper}.</li>
     *     <li>Builds a {@link ServerResponse} with status 200 (OK) and the response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the period ID in the path.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenGETPeriodByIdUseCase(ServerRequest serverRequest) {
        UUID id = UUID.fromString(serverRequest.pathVariable("id"));
        return useCase.getPeriodById(id)
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
