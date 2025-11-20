package pe.ask.university.api.handler.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.dto.request.StudentRequest;
import pe.ask.university.api.mapper.IStudentMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.api.utils.validator.CustomValidator;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.student.ISaveStudentUseCase;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Handles the HTTP POST request for creating a new student.
 * <p>
 * This handler is responsible for processing incoming requests to save a student.
 * It validates the request body, maps it to a domain model, invokes the corresponding
 * use case, and returns the appropriate server response.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class SaveStudentHandler {

    private final ISaveStudentUseCase useCase;
    private final IStudentMapper mapper;
    private final CustomValidator validator;

    /**
     * Listens for and processes the POST request to save a new student.
     * <p>
     * This method orchestrates the flow for creating a student:
     * <ol>
     *     <li>Extracts the {@link StudentRequest} from the request body.</li>
     *     <li>Validates the request object using {@link CustomValidator}.</li>
     *     <li>Maps the DTO to the domain model using {@link IStudentMapper}.</li>
     *     <li>Invokes the {@link ISaveStudentUseCase} to persist the student.</li>
     *     <li>Maps the resulting domain model back to a response DTO.</li>
     *     <li>Builds a {@link ServerResponse} with status 201 (Created) and the response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the student data.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenPOSTSaveStudentUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(StudentRequest.class)
                .flatMap(validator::validate)
                .map(mapper::toDomain)
                .flatMap(useCase::saveStudent)
                .map(mapper::toResponse)
                .flatMap(response ->
                        ServerResponse
                                .created(URI.create(""))
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(response)
                )
                .onErrorResume(ex ->
                        Mono.error(ex instanceof BaseException ? ex : new UnexpectedException(ex))
                );
    }
}
