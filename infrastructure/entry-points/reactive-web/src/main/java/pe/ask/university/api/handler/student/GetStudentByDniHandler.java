package pe.ask.university.api.handler.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.mapper.IStudentMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.student.IGetStudentByDniUseCase;
import reactor.core.publisher.Mono;

/**
 * Handles the HTTP GET request for retrieving a student by their DNI.
 * <p>
 * This handler is responsible for processing requests to fetch a single student
 * based on their DNI. It extracts the DNI from the request path, invokes the
 * appropriate use case, maps the result to a response DTO, and constructs the
 * server response.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class GetStudentByDniHandler {

    private final IGetStudentByDniUseCase useCase;
    private final IStudentMapper mapper;

    /**
     * Listens for and processes the GET request to retrieve a student by their DNI.
     * <p>
     * This method orchestrates the flow for fetching a student:
     * <ol>
     *     <li>Extracts the student DNI from the request's path variable.</li>
     *     <li>Invokes the {@link IGetStudentByDniUseCase} to get the student.</li>
     *     <li>Maps the resulting domain model to a response DTO using {@link IStudentMapper}.</li>
     *     <li>Builds a {@link ServerResponse} with status 200 (OK) and the response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the student DNI in the path.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenGETStudentByDniUseCase(ServerRequest serverRequest) {
        String dni = serverRequest.pathVariable("dni");
        return useCase.getStudentByDni(dni)
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
