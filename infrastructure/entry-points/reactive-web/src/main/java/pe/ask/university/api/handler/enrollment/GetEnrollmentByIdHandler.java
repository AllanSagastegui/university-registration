package pe.ask.university.api.handler.enrollment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.mapper.IEnrollmentMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.enrollment.IGetEnrollmentByIdUseCase;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Handles the HTTP GET request for retrieving an enrollment by its ID.
 * <p>
 * This handler is responsible for processing requests to fetch a single enrollment
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
public class GetEnrollmentByIdHandler {

    private final IGetEnrollmentByIdUseCase useCase;
    private final IEnrollmentMapper mapper;

    /**
     * Listens for and processes the GET request to retrieve an enrollment by its ID.
     * <p>
     * This method orchestrates the flow for fetching an enrollment:
     * <ol>
     *     <li>Extracts the enrollment ID from the request's path variable.</li>
     *     <li>Invokes the {@link IGetEnrollmentByIdUseCase} to get the enrollment.</li>
     *     <li>Maps the resulting domain model to a response DTO using {@link IEnrollmentMapper}.</li>
     *     <li>Builds a {@link ServerResponse} with status 200 (OK) and the response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the enrollment ID in the path.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenGETEnrollmentByIdUseCase(ServerRequest serverRequest) {
        UUID id = UUID.fromString(serverRequest.pathVariable("id"));
        return useCase.getEnrollmentById(id)
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
