package pe.ask.university.api.handler.course;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.mapper.ICourseMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.course.IGetCourseByIdUseCase;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Handles the HTTP GET request for retrieving a course by its ID.
 * <p>
 * This handler is responsible for processing requests to fetch a single course
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
public class GetCourseByIdHandler {

    private final IGetCourseByIdUseCase useCase;
    private final ICourseMapper mapper;

    /**
     * Listens for and processes the GET request to retrieve a course by its ID.
     * <p>
     * This method orchestrates the flow for fetching a course:
     * <ol>
     *     <li>Extracts the course ID from the request's path variable.</li>
     *     <li>Invokes the {@link IGetCourseByIdUseCase} to get the course.</li>
     *     <li>Maps the resulting domain model to a response DTO using {@link ICourseMapper}.</li>
     *     <li>Builds a {@link ServerResponse} with status 200 (OK) and the response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the course ID in the path.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenGETCourseByIdUseCase(ServerRequest serverRequest) {
        UUID id = UUID.fromString(serverRequest.pathVariable("id"));
        return useCase.getCourseById(id)
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
