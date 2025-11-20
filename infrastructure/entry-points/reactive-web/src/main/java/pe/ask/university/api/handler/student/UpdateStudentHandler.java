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
import pe.ask.university.port.in.usecase.student.IUpdateStudentUseCase;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Handles the HTTP PUT request for updating an existing student.
 * <p>
 * This handler is responsible for processing incoming requests to update a student.
 * It validates the request body, maps it to a domain model, invokes the corresponding
 * use case with the student's ID, and returns the appropriate server response.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class UpdateStudentHandler {

    private final IUpdateStudentUseCase useCase;
    private final IStudentMapper mapper;
    private final CustomValidator validator;

    /**
     * Listens for and processes the PUT request to update a student.
     * <p>
     * This method orchestrates the flow for updating a student:
     * <ol>
     *     <li>Extracts the student ID from the request's path variable.</li>
     *     <li>Extracts the {@link StudentRequest} from the request body.</li>
     *     <li>Validates the request object using {@link CustomValidator}.</li>
     *     <li>Maps the DTO to the domain model using {@link IStudentMapper}.</li>
     *     <li>Invokes the {@link IUpdateStudentUseCase} to update the student.</li>
     *     <li>Maps the resulting domain model back to a response DTO.</li>
     *     <li>Builds a {@link ServerResponse} with status 200 (OK) and the response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the student ID and updated data.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenPUTUpdateStudentUseCase(ServerRequest serverRequest) {
        UUID id = UUID.fromString(serverRequest.pathVariable("id"));
        return serverRequest.bodyToMono(StudentRequest.class)
                .flatMap(validator::validate)
                .map(mapper::toDomain)
                .flatMap(student -> useCase.updateStudent(id, student))
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
