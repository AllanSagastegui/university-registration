package pe.ask.university.api.handler.enrollment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.ask.university.api.dto.request.EnrollmentRequest;
import pe.ask.university.api.mapper.IEnrollmentMapper;
import pe.ask.university.api.utils.exception.UnexpectedException;
import pe.ask.university.api.utils.validator.CustomValidator;
import pe.ask.university.model.utils.exception.BaseException;
import pe.ask.university.port.in.usecase.enrollment.ISaveEnrollmentUseCase;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * Handles the HTTP POST request for creating a new enrollment.
 * <p>
 * This handler is responsible for processing incoming requests to save an enrollment.
 * It validates the request body, maps it to a domain model, invokes the corresponding
 * use case, and returns the appropriate server response.
 * </p>
 *
 * @author Allan Sagastegui
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class SaveEnrollmentHandler {

    private final ISaveEnrollmentUseCase useCase;
    private final IEnrollmentMapper mapper;
    private final CustomValidator validator;

    /**
     * Listens for and processes the POST request to save a new enrollment.
     * <p>
     * This method orchestrates the flow for creating an enrollment:
     * <ol>
     *     <li>Extracts the {@link EnrollmentRequest} from the request body.</li>
     *     <li>Validates the request object using {@link CustomValidator}.</li>
     *     <li>Maps the DTO to the domain model using {@link IEnrollmentMapper}.</li>
     *     <li>Invokes the {@link ISaveEnrollmentUseCase} to persist the enrollment.</li>
     *     <li>Maps the resulting domain model back to a response DTO.</li>
     *     <li>Builds a {@link ServerResponse} with status 201 (Created) and the response body.</li>
     * </ol>
     * Any exceptions during the process are handled, wrapping unexpected errors in a {@link UnexpectedException}.
     * </p>
     *
     * @param serverRequest The incoming server request containing the enrollment data.
     * @return A {@link Mono<ServerResponse>} that emits the response upon completion.
     */
    public Mono<ServerResponse> listenPOSTSaveEnrollmentUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(EnrollmentRequest.class)
                .flatMap(validator::validate)
                .map(mapper::toDomain)
                .flatMap(useCase::saveEnrollment)
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
